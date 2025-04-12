package org.wms.order.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.wms.common.model.Result;
import org.wms.order.mapper.PickingOrderMapper;
import org.wms.order.model.dto.PickingOrderDto;
import org.wms.order.model.entity.PickingOrder;
import org.wms.order.service.PickingOrderService;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @author moyu
 * @description 针对表【picking_order(拣货单表)】的数据库操作Service实现
 * @createDate 2025-04-12 14:41:26
 */
@Service
public class PickingOrderServiceImpl extends ServiceImpl<PickingOrderMapper, PickingOrder>
        implements PickingOrderService {

    @Override
    public Result<Page<PickingOrder>> pageList(PickingOrderDto dto) {
        // 构建查询条件
        LambdaQueryWrapper<PickingOrder> queryWrapper = new LambdaQueryWrapper<>();

        // 拣货单号模糊查询
        if (StringUtils.hasText(dto.getPickingNo())) {
            queryWrapper.like(PickingOrder::getPickingNo, dto.getPickingNo());
        }

        // 精确查询拣货人员
        if (StringUtils.hasText(dto.getPicker())) {
            queryWrapper.eq(PickingOrder::getPicker, dto.getPicker());
        }

        // 根据状态查询
        if (dto.getStatus() != null) {
            queryWrapper.eq(PickingOrder::getStatus, dto.getStatus());
        }

        // 根据排序条件进行排序
        if (dto.getTotalOrdersAsc() != null) {
            if (dto.getTotalOrdersAsc()) {
                queryWrapper.orderByAsc(PickingOrder::getTotalOrders);
            } else {
                queryWrapper.orderByDesc(PickingOrder::getTotalOrders);
            }
        }

        if (dto.getTotalItemsAsc() != null) {
            if (dto.getTotalItemsAsc()) {
                queryWrapper.orderByAsc(PickingOrder::getTotalItems);
            } else {
                queryWrapper.orderByDesc(PickingOrder::getTotalItems);
            }
        }

        if (dto.getTotalQuantityAsc() != null) {
            if (dto.getTotalQuantityAsc()) {
                queryWrapper.orderByAsc(PickingOrder::getTotalQuantity);
            } else {
                queryWrapper.orderByDesc(PickingOrder::getTotalQuantity);
            }
        }

        if (dto.getCreateTimeAsc() != null) {
            if (dto.getCreateTimeAsc()) {
                queryWrapper.orderByAsc(PickingOrder::getCreateTime);
            } else {
                queryWrapper.orderByDesc(PickingOrder::getCreateTime);
            }
        } else {
            // 默认按创建时间降序排序
            queryWrapper.orderByDesc(PickingOrder::getCreateTime);
        }

        // 执行分页查询
        Page<PickingOrder> page = new Page<>(dto.getPage(), dto.getPageSize());
        Page<PickingOrder> pageResult = this.page(page, queryWrapper);

        return Result.success(pageResult, "查询成功");
    }
}




