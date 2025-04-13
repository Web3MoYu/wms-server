package org.wms.order.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.wms.api.client.UserClient;
import org.wms.common.entity.sys.User;
import org.wms.common.exception.BizException;
import org.wms.common.model.Result;
import org.wms.common.utils.IdGenerate;
import org.wms.order.mapper.PickingOrderMapper;
import org.wms.order.model.dto.PickingOrderDto;
import org.wms.order.model.entity.*;
import org.wms.order.model.enums.OrderStatusEnums;
import org.wms.order.model.enums.PickingStatus;
import org.wms.order.model.vo.OrderDetailVo;
import org.wms.order.model.vo.PickingDetailVo;
import org.wms.order.model.vo.PickingOrderVo;
import org.wms.order.service.*;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author moyu
 * @description 针对表【picking_order(拣货单表)】的数据库操作Service实现
 * @createDate 2025-04-12 14:41:26
 */
@Service
public class PickingOrderServiceImpl extends ServiceImpl<PickingOrderMapper, PickingOrder>
        implements PickingOrderService {

    @Resource
    IdGenerate idGenerate;

    @Resource
    UserClient userClient;

    @Resource
    OrderOutService orderOutService;

    @Resource
    PickingItemService pickingItemService;

    @Resource
    OrderOutItemService orderOutItemService;

    @Resource
    PickingOrderRelationService pickingRelationService;

    @Override
    public Result<Page<PickingOrderVo>> pageList(PickingOrderDto dto) {
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
        // 设置用户信息
        List<PickingOrderVo> list = pageResult.getRecords().stream().map(item -> {
            PickingOrderVo vo = new PickingOrderVo();
            BeanUtils.copyProperties(item, vo);
            if (StringUtils.hasText(item.getPicker())) {
                User user = userClient.getUserById(item.getPicker());
                vo.setPickingUser(user);
            }
            return vo;
        }).toList();
        Page<PickingOrderVo> res = new Page<>(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal());
        res.setRecords(list);
        return Result.success(res, "查询成功");
    }

    @Override
    public Result<String> batchAddPickings(List<String> ids, String picker) {
        String pickId = String.valueOf(IdWorker.getId());
        List<OrderOut> orderOutList = orderOutService.lambdaQuery().in(OrderOut::getId, ids).list();
        int totalItems = 0;
        int totalQuantity = 0;
        List<PickingOrderRelation> relations = new ArrayList<>();
        String pickingNo = idGenerate.generatePickingNo();
        // 1.插入picking_item
        for (OrderOut orderOut : orderOutList) {
            // 获取订单详情
            List<OrderOutItem> itemList = orderOutItemService.lambdaQuery()
                    .eq(OrderOutItem::getOrderId, orderOut.getId()).list();

            // 计算总商品种类和数量
            totalItems += itemList.size();
            for (OrderOutItem item : itemList) {
                totalQuantity += item.getExpectedQuantity();
            }

            List<PickingItem> list = itemList.stream().map(item -> {
                PickingItem pickingItem = new PickingItem();
                pickingItem.setPickingId(pickId);
                pickingItem.setOrderId(orderOut.getId());
                pickingItem.setOrderItemId(item.getId());
                pickingItem.setProductId(item.getProductId());
                pickingItem.setProductName(item.getProductName());
                pickingItem.setProductCode(item.getProductCode());
                pickingItem.setBatchNumber(item.getBatchNumber());
                pickingItem.setExpectedQuantity(item.getExpectedQuantity());
                pickingItem.setStatus(PickingStatus.UNPICKING);
                pickingItem.setCreateTime(LocalDateTime.now());
                pickingItem.setUpdateTime(LocalDateTime.now());
                return pickingItem;
            }).toList();

            boolean b = pickingItemService.saveBatch(list);
            if (!b) {
                throw new BizException("保存拣货详情失败");
            }

            // 设置picking_order_item列表

            PickingOrderRelation relation = new PickingOrderRelation();
            relation.setPickingId(pickId);
            relation.setPickingNo(pickingNo);
            relation.setOrderId(orderOut.getId());
            relation.setOrderNo(orderOut.getOrderNo());
            relation.setStatus(PickingStatus.UNPICKING);
            relation.setCreateTime(LocalDateTime.now());
            relation.setUpdateTime(LocalDateTime.now());
            relations.add(relation);
        }

        // 2.插入picking_order
        // 获取订单总数量和
        PickingOrder pickingOrder = new PickingOrder();
        pickingOrder.setId(pickId);
        pickingOrder.setPickingNo(pickingNo);
        pickingOrder.setPicker(picker);
        pickingOrder.setStatus(PickingStatus.UNPICKING);
        pickingOrder.setTotalOrders(ids.size());
        pickingOrder.setTotalItems(totalItems);
        pickingOrder.setTotalQuantity(totalQuantity);
        pickingOrder.setCreateTime(LocalDateTime.now());
        pickingOrder.setUpdateTime(LocalDateTime.now());
        boolean pickOrderSave = this.save(pickingOrder);
        // 3.插入picking_order_relation
        boolean relationSave = pickingRelationService.saveBatch(relations);
        if (!relationSave || !pickOrderSave) {
            throw new BizException("保存拣货信息失败");
        }
        // 修改订单状态
        for (String id : ids) {
            orderOutService.updateStatus(id, null, OrderStatusEnums.IN_PROGRESS);
        }
        return Result.success(null, "添加拣货单成功");
    }

    @Override
    public Result<List<PickingDetailVo>> detail(String pickingId) {
        // 获取拣货单关系详情信息
        List<PickingOrderRelation> relations = pickingRelationService.lambdaQuery()
                .eq(PickingOrderRelation::getPickingId, pickingId)
                .list();
        List<PickingDetailVo> res = new ArrayList<>();
        for (PickingOrderRelation relation : relations) {
            PickingDetailVo vo = new PickingDetailVo();
            // 获取orderOut
            OrderOut orderOut = orderOutService.getById(relation.getOrderId());
            vo.setOrder(orderOut);
            // 获取出库单详情信息
            List<OrderDetailVo<OrderOutItem>> data = orderOutService.outDetail(relation.getOrderId()).getData();
            vo.setOrderInfo(data);
            // 获取分拣详情
            List<PickingItem> items = pickingItemService.lambdaQuery()
                    .eq(PickingItem::getPickingId, relation.getPickingId())
                    .eq(PickingItem::getOrderId, relation.getOrderId())
                    .list();
            vo.setPickingItems(items);
            res.add(vo);
        }
        return Result.success(res, "查询详情信息成功");
    }
}




