package org.wms.stock.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.wms.api.client.LocationClient;
import org.wms.api.client.UserClient;
import org.wms.common.entity.location.Area;
import org.wms.common.entity.stock.Stock;
import org.wms.common.entity.sys.User;
import org.wms.common.model.vo.LocationVo;
import org.wms.common.model.vo.StockVo;
import org.wms.stock.model.dto.MovementDto;
import org.wms.stock.model.entity.Movement;
import org.wms.stock.model.vo.MovementVo;
import org.wms.stock.service.MovementService;
import org.wms.stock.mapper.MovementMapper;
import org.springframework.stereotype.Service;
import org.wms.stock.service.StockService;

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
    StockService stockService;

    @Resource
    LocationClient locationClient;


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
                .orderByDesc(Movement::getMovementTime)
                .page(new Page<>(dto.getPage(), dto.getPageSize()));

        // 转化为vo
        List<MovementVo> list = page.getRecords().stream().map(this::assembleMovement).toList();
        Page<MovementVo> res = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        res.setRecords(list);
        return res;
    }


    /**
     * 组装VO, 将实体类变为VO对象
     *
     * @param movement 实体类
     * @return VO
     */
    public MovementVo assembleMovement(Movement movement) {
        MovementVo res = new MovementVo();
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
        res.setOpeatorUser(operator);
        res.setApproverUser(approver);

        return res;
    }
}




