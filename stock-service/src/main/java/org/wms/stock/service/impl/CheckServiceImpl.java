package org.wms.stock.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
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
import org.wms.common.enums.msg.MsgBizEnums;
import org.wms.common.enums.msg.MsgEnums;
import org.wms.common.enums.msg.MsgPriorityEnums;
import org.wms.common.enums.msg.MsgTypeEnums;
import org.wms.common.exception.BizException;
import org.wms.common.model.vo.StockVo;
import org.wms.common.utils.IdGenerate;
import org.wms.security.util.SecurityUtil;
import org.wms.stock.model.dto.AddCheckDto;
import org.wms.stock.model.dto.CheckQueryDto;
import org.wms.stock.model.dto.StockCheckDto;
import org.wms.stock.model.entity.Check;
import org.wms.stock.model.entity.CheckItem;
import org.wms.stock.model.enums.CheckDiffStatus;
import org.wms.stock.model.enums.CheckStatus;
import org.wms.stock.model.vo.CheckItemVo;
import org.wms.stock.model.vo.CheckVo;
import org.wms.stock.service.CheckItemService;
import org.wms.stock.service.CheckService;
import org.wms.stock.mapper.CheckMapper;
import org.springframework.stereotype.Service;
import org.wms.stock.service.StockService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author moyu
 * @description 针对表【stock_check(盘点单表)】的数据库操作Service实现
 * @createDate 2025-05-05 09:16:05
 */
@Service
public class CheckServiceImpl extends ServiceImpl<CheckMapper, Check>
        implements CheckService {

    @Resource
    UserClient userClient;

    @Resource
    IdGenerate idGenerate;

    @Resource
    StockService stockService;

    @Resource
    RabbitTemplate rabbitTemplate;

    @Resource
    LocationClient locationClient;

    @Resource
    CheckItemService checkItemService;

    @Override
    public Page<CheckVo> pageList(CheckQueryDto dto) {
        Page<Check> page = this.lambdaQuery()
                .like(StringUtils.hasText(dto.getCheckNo()), Check::getCheckNo, dto.getCheckNo())
                .eq(StringUtils.hasText(dto.getAreaId()), Check::getAreaId, dto.getAreaId())
                .eq(StringUtils.hasText(dto.getChecker()), Check::getChecker, dto.getChecker())
                .eq(StringUtils.hasText(dto.getCreator()), Check::getCreator, dto.getCreator())
                .ge(Objects.nonNull(dto.getPlanStartTime()), Check::getPlanStartTime, dto.getPlanStartTime())
                .le(Objects.nonNull(dto.getPlanEndTime()), Check::getPlanEndTime, dto.getPlanEndTime())
                .ge(Objects.nonNull(dto.getActualStartTime()), Check::getActualStartTime, dto.getActualStartTime())
                .le(Objects.nonNull(dto.getActualEndTime()), Check::getActualEndTime, dto.getActualEndTime())
                .eq(Objects.nonNull(dto.getStatus()), Check::getStatus, dto.getStatus())
                .orderByDesc(Check::getCreateTime)
                .page(new Page<>(dto.getPage(), dto.getPageSize()));
        List<CheckVo> list = page.getRecords().stream().map(this::assembleCheckVo).toList();
        Page<CheckVo> pageVo = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        pageVo.setRecords(list);
        return pageVo;
    }

    @Override
    public String addCheck(AddCheckDto dto) {
        String creator = SecurityUtil.getUserID();
        User checker = locationClient.getMainInspector(dto.getAreaId());

        List<Stock> list = stockService.lambdaQuery().eq(Stock::getAreaId, dto.getAreaId()).list();
        // check
        Check check = new Check();
        check.setId(IdWorker.getIdStr());
        check.setCheckNo(idGenerate.generateStockCheckNo());
        check.setAreaId(dto.getAreaId());
        check.setCreator(creator);
        check.setChecker(checker.getUserId());
        check.setPlanStartTime(dto.getPlanStartTime());
        check.setPlanEndTime(dto.getPlanEndTime());
        check.setStatus(CheckStatus.WAIT_CHECK);
        check.setRemark(dto.getRemark());
        check.setCreateTime(LocalDateTime.now());
        check.setUpdateTime(LocalDateTime.now());
        boolean checkSave = this.save(check);
        // checkItem
        List<CheckItem> checkItems = list.stream().map(item -> {
            CheckItem checkItem = new CheckItem();
            checkItem.setCheckId(check.getId());
            checkItem.setStockId(item.getId());
            checkItem.setSystemQuantity(item.getQuantity());
            checkItem.setStatus(CheckStatus.WAIT_CHECK);
            checkItem.setRemark(dto.getRemark());
            checkItem.setCreateTime(LocalDateTime.now());
            checkItem.setUpdateTime(LocalDateTime.now());
            return checkItem;
        }).toList();

        boolean checkItemSave = checkItemService.saveBatch(checkItems);
        if (!checkItemSave || !checkSave) {
            throw new BizException("新增盘点失败");
        }


        // 通知操作人进行变更
        User from = userClient.getUserById(creator);
        Msg msg = new Msg(MsgTypeEnums.STOCK_CHECK, "库存盘点", "你有一条库位盘点消息", checker.getUserId(),
                checker.getRealName(), from.getUserId(), from.getRealName(), MsgPriorityEnums.NORMAL, check.getCheckNo(),
                MsgBizEnums.STOCK_CHECK);
        rabbitTemplate.convertAndSend(MQConstant.EXCHANGE_NAME, MQConstant.ROUTING_KEY,
                new WsMsgDataVO<>(msg, MsgEnums.NOTICE.getCode(), checker.getUserId()));

        return "新增成功";
    }

    @Override
    public List<CheckItemVo> detail(String id) {

        List<CheckItem> list = checkItemService.lambdaQuery().eq(CheckItem::getCheckId, id).list();
        return list.stream().map(item -> {
            CheckItemVo vo = new CheckItemVo();
            BeanUtils.copyProperties(item, vo);
            Stock stock = stockService.getById(item.getStockId());
            StockVo stockVo = stockService.convertToStockVo(stock);
            vo.setStock(stockVo);
            return vo;
        }).toList();
    }

    @Override
    public String startCheck(List<StockCheckDto> dto) {
        String checkId = checkItemService.getById(dto.get(0).getCheckItemId()).getCheckId();
        // 修改盘点信息
        boolean checkUpdate = this.lambdaUpdate().eq(Check::getId, checkId)
                .set(Check::getActualStartTime, LocalDateTime.now())
                .set(Check::getStatus, CheckStatus.WAIT_CONFIRM)
                .update();

        List<CheckItem> list = dto.stream().map(item -> {
            CheckItem checkItem = checkItemService.getById(item.getCheckItemId());
            checkItem.setActualQuantity(item.getActualQuantity());
            checkItem.setDifferenceQuantity(checkItem.getSystemQuantity() - item.getActualQuantity());
            checkItem.setStatus(CheckStatus.COMPLETED);
            if (checkItem.getDifferenceQuantity() == 0) {
                checkItem.setIsDifference(CheckDiffStatus.NO);
            } else {
                checkItem.setIsDifference(CheckDiffStatus.YES);
            }
            return checkItem;
        }).toList();

        boolean checkItemUpdate = checkItemService.updateBatchById(list);

        if (!checkUpdate || !checkItemUpdate) {
            throw new BizException("盘点失败");
        }
        return "盘点成功";
    }

    @Override
    public String cancelCheck(String id) {
        boolean checkUpdate = this.lambdaUpdate()
                .eq(Check::getId, id)
                .set(Check::getActualStartTime, LocalDateTime.now())
                .set(Check::getActualEndTime, LocalDateTime.now())
                .set(Check::getStatus, CheckStatus.CANCELED)
                .update();

        boolean checkItemUpdate = checkItemService.lambdaUpdate()
                .eq(CheckItem::getCheckId, id)
                .set(CheckItem::getStatus, CheckStatus.CANCELED)
                .update();

        if (!checkUpdate || !checkItemUpdate) {
            throw new BizException("废弃失败");
        }
        return "废弃成功";
    }


    public CheckVo assembleCheckVo(Check check) {
        CheckVo vo = new CheckVo();
        BeanUtils.copyProperties(check, vo);

        Area area = locationClient.getArea(check.getAreaId());
        vo.setArea(area);

        User checker = userClient.getUserById(check.getChecker());
        User creator = userClient.getUserById(check.getCreator());
        vo.setCheckerUser(checker);
        vo.setCreatorUser(creator);
        return vo;
    }
}




