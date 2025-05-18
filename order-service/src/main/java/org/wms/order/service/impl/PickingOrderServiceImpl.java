package org.wms.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.wms.api.client.LocationClient;
import org.wms.api.client.StockClient;
import org.wms.api.client.UserClient;
import org.wms.common.constant.MQConstant;
import org.wms.common.entity.location.Area;
import org.wms.common.entity.location.Storage;
import org.wms.common.entity.msg.Msg;
import org.wms.common.entity.msg.WsMsgDataVO;
import org.wms.common.entity.order.InspectionItem;
import org.wms.common.entity.stock.Stock;
import org.wms.common.entity.sys.User;
import org.wms.common.enums.inspect.InspectType;
import org.wms.common.enums.location.LocationStatusEnums;
import org.wms.common.enums.msg.MsgBizEnums;
import org.wms.common.enums.msg.MsgEnums;
import org.wms.common.enums.msg.MsgPriorityEnums;
import org.wms.common.enums.msg.MsgTypeEnums;
import org.wms.common.exception.BizException;
import org.wms.common.model.Location;
import org.wms.common.model.Result;
import org.wms.common.model.vo.LocationInfo;
import org.wms.common.model.vo.LocationVo;
import org.wms.common.utils.IdGenerate;
import org.wms.common.utils.JsonUtils;
import org.wms.common.utils.TimeUtils;
import org.wms.order.mapper.InspectionItemMapper;
import org.wms.order.mapper.InspectionMapper;
import org.wms.order.mapper.OrderOutItemMapper;
import org.wms.order.mapper.PickingOrderMapper;
import org.wms.order.model.dto.BatchAddPickingDto;
import org.wms.order.model.dto.PickingOneDto;
import org.wms.order.model.dto.PickingOrderDto;
import org.wms.order.model.entity.*;
import org.wms.order.model.enums.OrderStatusEnums;
import org.wms.order.model.enums.PickingStatus;
import org.wms.order.model.enums.QualityStatusEnums;
import org.wms.order.model.vo.*;
import org.wms.order.service.*;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    StockClient stockClient;

    @Resource
    RabbitTemplate rabbitTemplate;

    @Resource
    LocationClient locationClient;

    @Resource
    OrderOutService orderOutService;

    @Resource
    InspectionMapper inspectionMapper;

    @Resource
    PickingOrderMapper pickingOrderMapper;

    @Resource
    OrderOutItemMapper orderOutItemMapper;

    @Resource
    PickingItemService pickingItemService;

    @Resource
    InspectionItemMapper inspectionItemMapper;

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
    public Result<String> batchAddPickings(BatchAddPickingDto dto) {
        List<String> ids = dto.getIds();
        String picker = dto.getPicker();
        String remark = dto.getRemark();
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
        pickingOrder.setRemark(remark);
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

            List<PickingItemVo> pickingItemVos = items.stream().map(item -> {
                PickingItemVo itemVo = new PickingItemVo();
                BeanUtils.copyProperties(item, itemVo);

                if (StringUtils.hasLength(item.getAreaId())) {
                    Area area = locationClient.getArea(item.getAreaId());
                    itemVo.setAreaName(area.getAreaName());
                }
                List<Location> locations = item.getLocation();
                if (locations != null && !locations.isEmpty()) {
                    List<LocationVo> list = locations.stream().map(location -> locationClient.getLocations(location)).toList();
                    itemVo.setLocations(list);
                }
                return itemVo;
            }).toList();
            vo.setPickingItems(pickingItemVos);
            res.add(vo);
        }
        return Result.success(res, "查询详情信息成功");
    }

    @Override
    public Result<List<PickingLocation>> getPickingLocation(String orderId) {
        // 获取当前订单的picking_item
        List<PickingItem> list = pickingItemService.lambdaQuery().eq(PickingItem::getOrderId, orderId).list();

        // 针对每一个订单查询库存信息
        List<PickingLocation> res = new ArrayList<>();
        for (PickingItem pickingItem : list) {
            PickingLocation pickingLocation = new PickingLocation();
            pickingLocation.setItemId(pickingItem.getId());
            // 获取库存信息
            Stock stock = stockClient.getStockByProductIdAndBatch(pickingItem.getProductId(), pickingItem.getBatchNumber());
            // 获取库存信息
            List<Location> locations = stock.getLocation();
            List<LocationInfo> locationInfos = new ArrayList<>();
            for (Location location : locations) {
                LocationInfo locationInfo = locationClient.getLocationInfo(location);
                locationInfos.add(locationInfo);
            }
            // 获取区域信息
            Area area = locationClient.getArea(stock.getAreaId());
            pickingLocation.setArea(area);
            // 组装
            pickingLocation.setLocations(locationInfos);
            res.add(pickingLocation);
        }
        return Result.success(res, "查询信息成功");
    }

    @Override
    public Result<String> pickOne(List<PickingOneDto> dto) {
        // 后面处理有用
        String pickId = null;
        String orderOutId = null;
        Map<String, PickingItem> map = new HashMap<>();

        for (PickingOneDto one : dto) {
            Set<String> set = one.getSet();
            String itemId = one.getItemId();
            String remark = one.getRemark();
            Integer count = one.getCount();
            String areaId = one.getAreaId();

            List<LocationInfo> locations = one.getLocation();
            PickingItem pickingItem = pickingItemService.getById(itemId);
            pickId = pickingItem.getPickingId();
            orderOutId = pickingItem.getOrderId();


            // 修改数量和状态以及location
            List<Location> loc = locations.stream().map(item -> {
                Location location = new Location();
                location.setShelfId(item.getShelf().getId());
                List<String> list = item.getStorages().stream().map(Storage::getId).toList();
                location.setStorageIds(list);
                return location;
            }).toList();
            boolean pickItemUpdate = pickingItemService.lambdaUpdate()
                    .eq(PickingItem::getId, itemId)
                    .set(PickingItem::getAreaId, areaId)
                    .set(PickingItem::getLocation, JsonUtils.toJsonString(loc))
                    .set(PickingItem::getActualQuantity, count)
                    .set(PickingItem::getStatus, PickingStatus.PICKED)
                    .set(PickingItem::getRemark, remark)
                    .set(PickingItem::getPickingTime, LocalDateTime.now()).update();

            // 修改拣货单状态
            boolean pickOrderUpdate = this.lambdaUpdate().eq(PickingOrder::getId, pickId)
                    .set(PickingOrder::getStatus, PickingStatus.PICKING).update();

            if (!pickOrderUpdate || !pickItemUpdate) {
                throw new BizException("拣货失败");
            }
            // 保存pickItem，以便插入质检信息使用
            PickingItem mapItem = pickingItemService.getById(itemId);
            map.put(mapItem.getOrderItemId(), mapItem);

            // 修改库存
            Stock stock = stockClient.getStockByProductIdAndBatch(pickingItem.getProductId(), pickingItem.getBatchNumber());
            // 修改数量
            stock.setQuantity(stock.getQuantity() - count);
            stock.setAvailableQuantity(stock.getAvailableQuantity() + pickingItem.getExpectedQuantity() - count);
            // 修改库位信息
            List<Location> stockLocations = stock.getLocation();
            List<Location> newLocations = new ArrayList<>();
            for (Location location : stockLocations) {
                Location newLocation = new Location();
                newLocation.setShelfId(location.getShelfId());
                List<String> storageIds = location.getStorageIds();
                List<String> newStorageIds = new ArrayList<>();
                for (String storageId : storageIds) {
                    if (!set.contains(storageId)) {
                        newStorageIds.add(storageId);
                    }
                }
                newLocation.setStorageIds(newStorageIds);
                newLocations.add(newLocation);
            }
            stock.setLocation(newLocations);
            // 修改库存信息
            boolean stockUpdate = stockClient.updateStock(stock);
            // 修改库位信息
            boolean storageUpdate = true;
            if (!set.isEmpty()) {
                storageUpdate = locationClient.updateStatusInIds(set, LocationStatusEnums.FREE.getCode(), null);
            }
            if (!stockUpdate || !storageUpdate) {
                throw new BizException("修改库位信息失败");
            }
        }

        Assert.hasLength(pickId, "pickId为空，业务异常");
        // 检查当前拣货单是否存在为拣货的数据
        List<PickingItem> list = pickingItemService.lambdaQuery()
                .eq(PickingItem::getPickingId, pickId)
                .eq(PickingItem::getStatus, PickingStatus.UNPICKING).list();
        if (list.isEmpty()) {
            // 修改拣货单状态
            boolean pickOrderUpdate = this.lambdaUpdate().eq(PickingOrder::getId, pickId)
                    .set(PickingOrder::getStatus, PickingStatus.PICKED).update();
            if (!pickOrderUpdate) {
                throw new BizException("修改拣货单失败");
            }
        }

        Assert.hasLength(orderOutId, "orderOutId为空, 业务异常");
        addInspectionInfo(orderOutId, map);
        return Result.success(null, "拣货成功");
    }

    @Override
    public List<PickingStatisticsVo> getOrderStatistics(String range) {
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = TimeUtils.getStartTime(range);
        String startStr = startTime.format(FORMATTER);
        String endStr = endTime.format(FORMATTER);
        List<PickingStatisticsVo> list = pickingOrderMapper.getOrderStatistics(startStr, endStr);
        for (PickingStatisticsVo vo : list) {
            if (Objects.equals(vo.getStatus(), PickingStatus.UNPICKING.getCode())) {
                vo.setStatusVo(PickingStatus.UNPICKING.getDesc());
            }
            if (Objects.equals(vo.getStatus(), PickingStatus.PICKING.getCode())) {
                vo.setStatusVo(PickingStatus.PICKING.getDesc());
            }
            if (Objects.equals(vo.getStatus(), PickingStatus.PICKED.getCode())) {
                vo.setStatusVo(PickingStatus.PICKED.getDesc());
            }
            if (Objects.equals(vo.getStatus(), PickingStatus.ERROR.getCode())) {
                vo.setStatusVo(PickingStatus.ERROR.getDesc());
            }
        }
        return list;
    }


    /**
     * 增加质检信息
     *
     * @param id  出库订单ID
     * @param map 数据记录
     */
    private void addInspectionInfo(String id, Map<String, PickingItem> map) {

        // 获取出库订单信息
        OrderOut orderOut = orderOutService.getById(id);

        // 添加质检信息
        LambdaUpdateWrapper<OrderOutItem> itemWrapper = new LambdaUpdateWrapper<>();
        itemWrapper.eq(OrderOutItem::getOrderId, id);
        List<OrderOutItem> inItems = orderOutItemMapper.selectList(itemWrapper);
        // 生成质检信息
        Inspection inspection = new Inspection();
        inspection.setInspectionNo(idGenerate.generateInspectionNo(InspectType.OUTBOUND_INSPECT));
        inspection.setInspectionType(InspectType.OUTBOUND_INSPECT);
        inspection.setRelatedOrderId(orderOut.getId());
        inspection.setRelatedOrderNo(orderOut.getOrderNo());
        inspection.setInspector(orderOut.getInspector());
        inspection.setStatus(QualityStatusEnums.NOT_INSPECTED);
        inspection.setCreateTime(LocalDateTime.now());
        inspection.setUpdateTime(LocalDateTime.now());
        // 插入质检信息
        int insert = inspectionMapper.insert(inspection);
        if (insert <= 0) {
            throw new BizException(303, "插入质检信息失败");
        }
        // 生成质检详情信息
        List<InspectionItem> list = inItems.stream().map((item) -> {
            InspectionItem inspectionItem = new InspectionItem();
            inspectionItem.setInspectionId(inspection.getId());
            inspectionItem.setProductId(item.getProductId());
            inspectionItem.setBatchNumber(item.getBatchNumber());
            // 这里处理数量和位置信息
            PickingItem pickingItem = map.get(item.getId());
            inspectionItem.setAreaId(pickingItem.getAreaId());
            inspectionItem.setLocation(pickingItem.getLocation());
            inspectionItem.setInspectionQuantity(pickingItem.getActualQuantity());
            inspectionItem.setCreateTime(LocalDateTime.now());
            inspectionItem.setUpdateTime(LocalDateTime.now());
            return inspectionItem;
        }).toList();

        // 修改出库订单详情区域和位置信息
        map.forEach((key, val) -> {
            String itemId = val.getOrderItemId();
            boolean update = orderOutItemService.lambdaUpdate().eq(OrderOutItem::getId, itemId)
                    .set(OrderOutItem::getAreaId, val.getAreaId())
                    .set(OrderOutItem::getLocation, JsonUtils.toJsonString(val.getLocation())).update();
            if (!update) {
                throw new BizException("修改出库详情失败");
            }
        });
        // 插入质检详情信息
        inspectionItemMapper.insert(list, list.size());

        // 生成消息
        User from = userClient.getUserById(orderOut.getApprover());
        User to = userClient.getUserById(orderOut.getInspector());
        Msg msg = new Msg(MsgTypeEnums.QUALITY_CHECK, "质检通知", "你有一笔入库质检订单", to.getUserId(),
                to.getRealName(), from.getUserId(), from.getRealName(), MsgPriorityEnums.NORMAL, inspection.getInspectionNo(),
                MsgBizEnums.QUALITY_CHECK);
        rabbitTemplate.convertAndSend(MQConstant.EXCHANGE_NAME, MQConstant.ROUTING_KEY,
                new WsMsgDataVO<>(msg, MsgEnums.NOTICE.getCode(), to.getUserId()));
    }

}




