package org.wms.stock.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.wms.api.client.LocationClient;
import org.wms.api.client.UserClient;
import org.wms.common.constant.MQConstant;
import org.wms.common.entity.location.Area;
import org.wms.common.entity.msg.Msg;
import org.wms.common.entity.msg.WsMsgDataVO;
import org.wms.common.entity.stock.Stock;
import org.wms.common.entity.sys.User;
import org.wms.common.enums.location.LocationStatusEnums;
import org.wms.common.enums.msg.MsgBizEnums;
import org.wms.common.enums.msg.MsgEnums;
import org.wms.common.enums.msg.MsgPriorityEnums;
import org.wms.common.enums.msg.MsgTypeEnums;
import org.wms.common.exception.BizException;
import org.wms.common.model.Location;
import org.wms.common.model.vo.LocationVo;
import org.wms.common.model.vo.StockVo;
import org.wms.common.utils.IdGenerate;
import org.wms.security.util.SecurityUtil;
import org.wms.stock.model.dto.AddMovementDto;
import org.wms.stock.model.dto.MovementDto;
import org.wms.stock.model.entity.Movement;
import org.wms.stock.model.enums.MovementStatus;
import org.wms.stock.model.vo.MovementVo;
import org.wms.stock.service.MovementService;
import org.wms.stock.mapper.MovementMapper;
import org.springframework.stereotype.Service;
import org.wms.stock.service.StockService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author moyu
 * @description 针对表【stock_movement(库位变动记录表)】的数据库操作Service实现
 * @createDate 2025-05-03 22:29:31
 */
@Service
public class MovementServiceImpl extends ServiceImpl<MovementMapper, Movement>
        implements MovementService {

    @Resource
    UserClient userClient;

    @Resource
    IdGenerate idGenerate;

    @Resource
    StockService stockService;

    @Resource
    LocationClient locationClient;

    @Resource
    RabbitTemplate rabbitTemplate;


    @Override
    public Page<MovementVo> pageMovement(MovementDto dto) {
        Page<Movement> page = this.lambdaQuery()
                .like(StringUtils.hasText(dto.getMovementNo()), Movement::getMovementNo, dto.getMovementNo())
                .eq(StringUtils.hasText(dto.getBeforeAreaId()), Movement::getBeforeAreaId, dto.getBeforeAreaId())
                .eq(StringUtils.hasText(dto.getAfterAreaId()), Movement::getAfterAreaId, dto.getAfterAreaId())
                .eq(StringUtils.hasText(dto.getOperator()), Movement::getOperator, dto.getOperator())
                .eq(StringUtils.hasText(dto.getApprover()), Movement::getApprover, dto.getApprover())
                .ge(dto.getStartDate() != null, Movement::getMovementTime, dto.getStartDate())
                .le(dto.getEndDate() != null, Movement::getMovementTime, dto.getEndDate())
                .eq(dto.getStatus() != null, Movement::getStatus, dto.getStatus())
                .orderByDesc(Movement::getCreateTime)
                .page(new Page<>(dto.getPage(), dto.getPageSize()));

        // 转化为vo
        List<MovementVo> list = page.getRecords().stream().map(this::assembleMovement).toList();
        Page<MovementVo> res = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        res.setRecords(list);
        return res;
    }

    @Override
    public String addMovement(AddMovementDto dto) {
        Stock stock = stockService.getById(dto.getStockId());
        String operator = SecurityUtil.getUserID();
        Area area = locationClient.getArea(dto.getAreaId());

        Movement movement = new Movement();
        movement.setMovementNo(idGenerate.generateStorageMovementNo());
        movement.setStockId(dto.getStockId());
        movement.setBeforeAreaId(stock.getAreaId());
        movement.setBeforeLocation(stock.getLocation());
        movement.setAfterAreaId(dto.getAreaId());
        movement.setAfterLocation(dto.getLocations());
        movement.setOperator(operator);
        movement.setApprover(area.getAreaManager());
        movement.setRemark(dto.getRemark());
        movement.setCreateTime(LocalDateTime.now());
        movement.setStatus(MovementStatus.PENDING);
        boolean save = this.save(movement);
        if (!save) {
            throw new BizException("新增库位变更失败");
        }

        // 通知审批人进行审批
        User from = userClient.getUserById(movement.getOperator());
        User to = userClient.getUserById(movement.getApprover());
        Msg msg = new Msg(MsgTypeEnums.STORAGE_CHANGE, "库位变更", "你有一条库位变更消息", to.getUserId(),
                to.getRealName(), from.getUserId(), from.getRealName(), MsgPriorityEnums.NORMAL, movement.getMovementNo(),
                MsgBizEnums.STORAGE_CHANGE);
        rabbitTemplate.convertAndSend(MQConstant.EXCHANGE_NAME, MQConstant.ROUTING_KEY,
                new WsMsgDataVO<>(msg, MsgEnums.NOTICE.getCode(), to.getUserId()));

        return "添加成功";
    }

    @Override
    public String approveMovement(String id) {
        String userId = SecurityUtil.getUserID();
        Movement movement = this.getById(id);
        if (!StrUtil.equals(userId, movement.getApprover())) {
            throw new BizException("没有权限");
        }
        movement.setStatus(MovementStatus.TO_BE_CHANGED);
        boolean b = this.updateById(movement);
        if (!b) {
            throw new BizException("审批失败");
        }

        // 通知操作人进行变更
        User from = userClient.getUserById(movement.getApprover());
        User to = userClient.getUserById(movement.getOperator());
        Msg msg = new Msg(MsgTypeEnums.STORAGE_CHANGE, "库位变更", "你有一条库位变更消息", to.getUserId(),
                to.getRealName(), from.getUserId(), from.getRealName(), MsgPriorityEnums.NORMAL, movement.getMovementNo(),
                MsgBizEnums.STORAGE_CHANGE);
        rabbitTemplate.convertAndSend(MQConstant.EXCHANGE_NAME, MQConstant.ROUTING_KEY,
                new WsMsgDataVO<>(msg, MsgEnums.NOTICE.getCode(), to.getUserId()));
        return "审批成功";
    }

    @Override
    public String rejectMovement(String id, String reason) {
        String userId = SecurityUtil.getUserID();
        Movement movement = this.getById(id);
        if (!StrUtil.equals(userId, movement.getApprover())) {
            throw new BizException("没有权限");
        }

        boolean update = this.lambdaUpdate().eq(Movement::getId, id)
                .set(Movement::getReason, reason)
                .set(Movement::getMovementTime, LocalDateTime.now())
                .set(Movement::getStatus, MovementStatus.REJECT).update();
        if (!update) {
            throw new BizException("拒绝失败");
        }
        return "拒绝成功";
    }

    @Override
    public String doneMovement(String id) {
        String userId = SecurityUtil.getUserID();
        Movement movement = this.getById(id);
        if (!StrUtil.equals(userId, movement.getOperator())) {
            throw new BizException("没有权限");
        }
        Stock stock = stockService.getById(movement.getStockId());

        movement.setBeforeLocation(stock.getLocation());
        movement.setStatus(MovementStatus.COMPLETED);
        movement.setMovementTime(LocalDateTime.now());

        // 修改库位信息
        List<Location> beforeLocation = movement.getBeforeLocation();
        // 释放原先的库位信息
        beforeLocation.forEach((item) -> {
            boolean update = locationClient.updateStatusInStorage(item, LocationStatusEnums.FREE.getCode(), null);
            if (!update) {
                throw new BizException("变更失败");
            }
        });

        // 覆盖新的库位信息
        List<Location> afterLocation = movement.getAfterLocation();
        afterLocation.forEach((item) -> {
            boolean update = locationClient.updateStatusInStorage(item, LocationStatusEnums.OCCUPIED.getCode(), stock.getProductId());
            if (!update) {
                throw new BizException("变更失败");
            }
        });

        // 更新状态以及库位信息
        boolean moveUpdate = this.updateById(movement);

        // 更新库存状态
        stock.setAreaId(movement.getAfterAreaId());
        stock.setLocation(movement.getAfterLocation());
        boolean stockUpdate = stockService.updateById(stock);

        if (!moveUpdate || !stockUpdate) {
            throw new BizException("变更失败");
        }
        return "变更成功";
    }


    /**
     * 组装VO, 将实体类变为VO对象
     *
     * @param movement 实体类
     * @return VO
     */
    public MovementVo assembleMovement(Movement movement) {
        MovementVo res = new MovementVo();
        BeanUtils.copyProperties(movement, res);
        Stock stock = stockService.getById(movement.getStockId());
        StockVo stockVo = stockService.convertToStockVo(stock);
        res.setStock(stockVo);

        Area beforeArea = locationClient.getArea(movement.getBeforeAreaId());
        List<LocationVo> beforeLocation = movement.getBeforeLocation().stream().map((item) -> locationClient.getLocations(item)).toList();
        res.setBeforeArea(beforeArea);
        res.setBeforeLocationVo(beforeLocation);

        Area afrerArea = locationClient.getArea(movement.getAfterAreaId());
        List<LocationVo> afterLocation = movement.getAfterLocation().stream().map((item) -> locationClient.getLocations(item)).toList();
        res.setAfterArea(afrerArea);
        res.setAfterLocationVo(afterLocation);

        User operator = userClient.getUserById(movement.getOperator());
        User approver = userClient.getUserById(movement.getApprover());
        res.setOperatorUser(operator);
        res.setApproverUser(approver);

        return res;
    }
}




