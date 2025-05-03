package org.wms.order.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;
import org.springframework.util.StringUtils;
import org.wms.api.client.AlertClient;
import org.wms.api.client.LocationClient;
import org.wms.api.client.StockClient;
import org.wms.api.client.UserClient;
import org.wms.common.constant.MQConstant;
import org.wms.common.entity.location.Area;
import org.wms.common.entity.msg.Msg;
import org.wms.common.entity.msg.WsMsgDataVO;
import org.wms.common.entity.stock.Alert;
import org.wms.common.entity.stock.Stock;
import org.wms.common.entity.sys.User;
import org.wms.common.enums.inspect.InspectType;
import org.wms.common.enums.location.LocationStatusEnums;
import org.wms.common.enums.msg.MsgBizEnums;
import org.wms.common.enums.msg.MsgEnums;
import org.wms.common.enums.msg.MsgPriorityEnums;
import org.wms.common.enums.msg.MsgTypeEnums;
import org.wms.common.enums.order.ReceiveStatus;
import org.wms.common.enums.stock.AlertStatusEnums;
import org.wms.common.exception.BizException;
import org.wms.common.model.Location;
import org.wms.common.model.Result;
import org.wms.common.utils.IdGenerate;
import org.wms.common.utils.JsonUtils;
import org.wms.order.mapper.InspectionItemMapper;
import org.wms.order.mapper.InspectionMapper;
import org.wms.order.model.dto.InBoundInspectDto;
import org.wms.order.model.dto.InspectionDto;
import org.wms.order.model.dto.ItemInspect;
import org.wms.order.model.dto.StockInDto;
import org.wms.order.model.entity.*;
import org.wms.common.entity.order.InspectionItem;
import org.wms.common.enums.order.InspectItemStatus;
import org.wms.order.model.enums.OrderStatusEnums;
import org.wms.order.model.enums.QualityStatusEnums;
import org.wms.order.model.vo.InspectionDetailVo;
import org.wms.order.model.vo.InspectionVo;
import org.wms.order.model.vo.OrderDetailVo;
import org.wms.order.service.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import jakarta.annotation.Resource;

/**
 * @author moyu
 * @description 针对表【quality_inspection(质检记录表)】的数据库操作Service实现
 * @createDate 2025-03-21 10:02:29
 */
@Service
public class InspectionServiceImpl extends ServiceImpl<InspectionMapper, Inspection>
        implements InspectionService {

    @Resource
    private UserClient userClient;

    @Resource
    private AlertClient alertClient;

    @Resource
    private StockClient stockClient;

    @Resource
    private IdGenerate idGenerate;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private LocationClient locationClient;

    @Resource
    private OrderInService orderInService;

    @Resource
    private OrderOutService orderOutService;

    @Resource
    private OrderInItemService orderInItemService;

    @Resource
    private OrderOutItemService orderOutItemService;

    @Resource
    private InspectionItemMapper inspectionItemMapper;

    @Override
    public Page<InspectionVo> pageList(InspectionDto dto) {
        // 构建查询条件
        LambdaQueryWrapper<Inspection> queryWrapper = new LambdaQueryWrapper<>();

        // 质检编号模糊搜索
        if (StringUtils.hasText(dto.getInspectionNo())) {
            queryWrapper.like(Inspection::getInspectionNo, dto.getInspectionNo());
        }

        // 质检类型：1-入库质检，2-出库质检，3-库存质检,为null查询全部
        if (dto.getInspectionType() != null) {
            queryWrapper.eq(Inspection::getInspectionType, dto.getInspectionType());
        }

        // 订单编号，模糊搜索
        if (StringUtils.hasText(dto.getRelatedOrderNo())) {
            queryWrapper.like(Inspection::getRelatedOrderNo, dto.getRelatedOrderNo());
        }

        // 质检员
        if (StringUtils.hasText(dto.getInspector())) {
            queryWrapper.eq(Inspection::getInspector, dto.getInspector());
        }

        // 质检时间范围
        if (dto.getStartTime() != null) {
            queryWrapper.ge(Inspection::getInspectionTime, dto.getStartTime());
        }
        if (dto.getEndTime() != null) {
            queryWrapper.le(Inspection::getInspectionTime, dto.getEndTime());
        }

        // 质检状态：1-通过，2-不通过，3-部分异常,为null查询全部
        if (dto.getStatus() != null) {
            queryWrapper.eq(Inspection::getStatus, dto.getStatus());
        }
        if (dto.getReceiveStatus() != null) {
            queryWrapper
                    .eq(Inspection::getReceiveStatus, dto.getReceiveStatus())
                    .eq(Inspection::getInspectionType, InspectType.INBOUND_INSPECT.getCode());
        }

        // 排序：创建时间升序或降序
        if (dto.getCreateTimeAsc() != null) {
            if (dto.getCreateTimeAsc()) {
                queryWrapper.orderByAsc(Inspection::getCreateTime);
            } else {
                queryWrapper.orderByDesc(Inspection::getCreateTime);
            }
        } else {
            // 默认降序
            queryWrapper.orderByDesc(Inspection::getCreateTime);
        }

        // 执行分页查询
        Page<Inspection> page = new Page<>(dto.getPage(), dto.getPageSize());
        Page<Inspection> pageResult = this.page(page, queryWrapper);

        // 将查询结果转换为VO对象
        List<InspectionVo> list = pageResult.getRecords().stream().map(item -> {
            InspectionVo vo = new InspectionVo();
            // 复制基本属性
            BeanUtils.copyProperties(item, vo);

            // 获取质检员信息
            if (StringUtils.hasText(item.getInspector())) {
                User user = userClient.getUserById(item.getInspector());
                vo.setInspectorInfo(user);
            }
            if (item.getInspectionType() == InspectType.INBOUND_INSPECT) {
                vo.setOrderStatus(
                        orderInService.lambdaQuery().eq(OrderIn::getId, item.getRelatedOrderId()).one().getStatus());
            }
            if (item.getInspectionType() == InspectType.OUTBOUND_INSPECT) {
                vo.setOrderStatus(
                        orderOutService.lambdaQuery().eq(OrderOut::getId, item.getRelatedOrderId()).one().getStatus());
            }

            return vo;
        }).toList();
        Page<InspectionVo> res = new Page<>(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal());
        res.setRecords(list);
        return res;
    }

    @Override
    public Result<String> inBoundCheck(InBoundInspectDto dto) {
        int status = 0;
        List<ItemInspect> list = dto.getItemInspects();
        if (list == null || list.isEmpty()) {
            throw new BizException(500, "参数不合法");
        }
        // 判断是全失败、全成功、还是部分成功
        for (ItemInspect item : list) {
            if (item.isApproval()) {
                status++;
            }
        }
        // 获取质检订单
        Inspection one = this.lambdaQuery().eq(Inspection::getInspectionNo, dto.getInspectionNo()).one();
        InspectType type = one.getInspectionType();
        // 修改状态
        // 全成功
        if (status == list.size()) {
            // 修改质检状态
            boolean update = this.updateInspectionStatus(dto.getRemark(), dto.getInspectionNo(),
                    QualityStatusEnums.PASSED);
            boolean update1 = false;
            if (type == InspectType.INBOUND_INSPECT) {
                // 订单状态
                orderInService.updateStatus(one.getRelatedOrderId(), dto.getRemark(), OrderStatusEnums.IN_PROGRESS);
                // 订单质检状态
                update1 = orderInService.lambdaUpdate().eq(OrderIn::getId, one.getRelatedOrderId())
                        .set(OrderIn::getQualityStatus, QualityStatusEnums.PASSED.getCode()).update();

            }
            if (type == InspectType.OUTBOUND_INSPECT) {
                // 订单状态
                orderOutService.updateStatus(one.getRelatedOrderId(), dto.getRemark(), OrderStatusEnums.IN_PROGRESS);
                // 订单质检状态
                update1 = orderOutService.lambdaUpdate().eq(OrderOut::getId, one.getRelatedOrderId())
                        .set(OrderOut::getQualityStatus, QualityStatusEnums.PASSED.getCode()).update();
            }

            if (!update || !update1) {
                throw new BizException(303, "修改质检状态失败");
            }
        } else if (status == 0) {
            boolean update = this.updateInspectionStatus(dto.getRemark(), dto.getInspectionNo(),
                    QualityStatusEnums.FAILED);
            boolean update1 = false;
            if (type == InspectType.INBOUND_INSPECT) {
                orderInService.updateStatus(one.getRelatedOrderId(), dto.getRemark(), OrderStatusEnums.COMPLETED);
                // 订单质检状态
                update1 = orderInService.lambdaUpdate().eq(OrderIn::getId, one.getRelatedOrderId())
                        .set(OrderIn::getQualityStatus, QualityStatusEnums.FAILED.getCode()).update();
            }

            if (type == InspectType.OUTBOUND_INSPECT) {
                orderOutService.updateStatus(one.getRelatedOrderId(), dto.getRemark(), OrderStatusEnums.COMPLETED);
                // 订单质检状态
                update1 = orderOutService.lambdaUpdate().eq(OrderOut::getId, one.getRelatedOrderId())
                        .set(OrderOut::getQualityStatus, QualityStatusEnums.FAILED.getCode()).update();
            }

            // 全失败
            if (!update || !update1) {
                throw new BizException(303, "修改质检状态失败");
            }
        } else {
            boolean update = this.updateInspectionStatus(dto.getRemark(),
                    dto.getInspectionNo(), QualityStatusEnums.PARTIALLY_EXCEPTIONAL);
            boolean update1 = false;
            if (type == InspectType.INBOUND_INSPECT) {
                orderInService.updateStatus(one.getRelatedOrderId(), dto.getRemark(), OrderStatusEnums.IN_PROGRESS);
                // 订单质检状态
                update1 = orderInService.lambdaUpdate().eq(OrderIn::getId, one.getRelatedOrderId())
                        .set(OrderIn::getQualityStatus, QualityStatusEnums.PARTIALLY_EXCEPTIONAL.getCode()).update();
            }
            if (type == InspectType.OUTBOUND_INSPECT) {
                orderOutService.updateStatus(one.getRelatedOrderId(), dto.getRemark(), OrderStatusEnums.IN_PROGRESS);
                // 订单质检状态
                update1 = orderOutService.lambdaUpdate().eq(OrderOut::getId, one.getRelatedOrderId())
                        .set(OrderOut::getQualityStatus, QualityStatusEnums.PARTIALLY_EXCEPTIONAL.getCode()).update();
            }
            if (!update || !update1) {
                throw new BizException(303, "修改质检状态失败");
            }
        }
        // 遍历订单详情，修改状态
        list.forEach(item -> {
            // 通过
            boolean r1;
            boolean r2 = false;
            InspectionItem inspectionItem = inspectionItemMapper.selectById(item.getItemId());
            if (item.isApproval()) {
                // 修改质检详情状态和数量
                r1 = this.inspectionItemMapper.updateItemStatusAndCount(item.getRemark(), item.getItemId(),
                        InspectItemStatus.QUALIFIED.getCode(), item.getCount(), item.getActualQuantity());


                // 修改订单详情状态
                if (type == InspectType.INBOUND_INSPECT) {
                    r2 = orderInItemService.lambdaUpdate()
                            .eq(OrderInItem::getOrderId, one.getRelatedOrderId())
                            .eq(OrderInItem::getProductId, item.getProductId())
                            .set(OrderInItem::getRemark, item.getRemark())
                            .set(OrderInItem::getActualQuantity, item.getCount())
                            .set(OrderInItem::getQualityStatus, QualityStatusEnums.PASSED).update();
                }

                if (type == InspectType.OUTBOUND_INSPECT) {
                    r2 = orderOutItemService.lambdaUpdate()
                            .eq(OrderOutItem::getOrderId, one.getRelatedOrderId())
                            .eq(OrderOutItem::getProductId, item.getProductId())
                            .set(OrderOutItem::getRemark, item.getRemark())
                            .set(OrderOutItem::getActualQuantity, item.getCount())
                            .set(OrderOutItem::getQualityStatus, QualityStatusEnums.PASSED).update();
                }
            } else {
                r1 = this.inspectionItemMapper.updateItemStatusAndCount(item.getRemark(), item.getItemId(),
                        InspectItemStatus.UNQUALIFIED.getCode(), item.getCount(), item.getActualQuantity());
                if (type == InspectType.INBOUND_INSPECT) {
                    // 修改订单详情状态
                    r2 = orderInItemService.lambdaUpdate()
                            .eq(OrderInItem::getOrderId, one.getRelatedOrderId())
                            .eq(OrderInItem::getProductId, item.getProductId())
                            .set(OrderInItem::getActualQuantity, item.getCount())
                            .set(OrderInItem::getRemark, item.getRemark())
                            .set(OrderInItem::getQualityStatus, QualityStatusEnums.FAILED).update();
                    // 将库位释放
                    // 查询库位信息
                    if (inspectionItem == null || inspectionItem.getLocation() == null
                            || inspectionItem.getLocation().isEmpty()) {
                        throw new BizException(500, "业务异常");
                    }
                    inspectionItem.getLocation().forEach(location -> {
                        boolean b = locationClient.updateStatusInStorage(location, LocationStatusEnums.FREE.getCode(),
                                null);
                        if (!b) {
                            throw new BizException(303, "修改库位信息失败");
                        }
                    });
                }
                if (type == InspectType.OUTBOUND_INSPECT) {
                    // 修改订单详情状态
                    r2 = orderOutItemService.lambdaUpdate()
                            .eq(OrderOutItem::getOrderId, one.getRelatedOrderId())
                            .eq(OrderOutItem::getProductId, item.getProductId())
                            .set(OrderOutItem::getActualQuantity, item.getCount())
                            .set(OrderOutItem::getRemark, item.getRemark())
                            .set(OrderOutItem::getQualityStatus, QualityStatusEnums.FAILED).update();
                }
            }
            if (!r1 || !r2) {
                throw new BizException(303, "修改详情状态失败");
            }
        });
        return Result.success(null, "质检成功");
    }

    @Override
    public Result<InspectionDetailVo<OrderInItem>> inDetail(String id) {
        Inspection one = this.lambdaQuery().eq(Inspection::getId, id).one();
        Result<List<OrderDetailVo<OrderInItem>>> result = orderInService.inDetail(one.getRelatedOrderId());
        if (result.getCode() != 200) {
            throw new BizException(303, "查询出错");
        }
        List<OrderDetailVo<OrderInItem>> data = result.getData();
        // 查询质检详情
        LambdaQueryWrapper<InspectionItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(InspectionItem::getInspectionId, one.getId());
        List<InspectionItem> inspectionItems = inspectionItemMapper.selectList(wrapper);
        // 封装vo
        InspectionDetailVo<OrderInItem> vo = new InspectionDetailVo<>();
        vo.setOrderDetail(data);
        vo.setInspectionItems(inspectionItems);
        return Result.success(vo, "查询成功");
    }

    @Override
    public Result<InspectionDetailVo<OrderOutItem>> outDetail(String id) {
        Inspection one = this.lambdaQuery().eq(Inspection::getId, id).one();
        Result<List<OrderDetailVo<OrderOutItem>>> result = orderOutService.outDetail(one.getRelatedOrderId());
        if (result.getCode() != 200) {
            throw new BizException(303, "查询出错");
        }
        List<OrderDetailVo<OrderOutItem>> data = result.getData();
        // 查询质检详情
        LambdaQueryWrapper<InspectionItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(InspectionItem::getInspectionId, one.getId());
        List<InspectionItem> inspectionItems = inspectionItemMapper.selectList(wrapper);
        // 封装vo
        InspectionDetailVo<OrderOutItem> vo = new InspectionDetailVo<>();
        vo.setOrderDetail(data);
        vo.setInspectionItems(inspectionItems);
        return Result.success(vo, "查询成功");
    }

    @Override
    public Result<String> stockOne(StockInDto dto) {
        // 获取原有的信息
        InspectionItem item = inspectionItemMapper.selectById(dto.getItemId());
        // 获取质检信息
        Inspection inspection = this.getById(item.getInspectionId());
        // 释放原先的库位
        item.getLocation().forEach((location) -> {
            boolean b = locationClient.updateStatusInStorage(location, LocationStatusEnums.FREE.getCode(), null);
            if (!b) {
                throw new BizException("释放库位信息失败");
            }
        });
        // 修改当前质检详情状态和位置信息
        LambdaUpdateWrapper<InspectionItem> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(InspectionItem::getId, dto.getItemId())
                .set(InspectionItem::getLocation, JsonUtils.toJsonString(dto.getLocations()))
                .set(InspectionItem::getReceiveStatus, ReceiveStatus.DONE);
        int update1 = inspectionItemMapper.update(wrapper);
        if (update1 != 1) {
            throw new BizException("修改质检详情状态和位置信息失败");
        }
        // 修改订单详情位置信息
        boolean update2 = orderInItemService.lambdaUpdate()
                .eq(OrderInItem::getOrderId, inspection.getRelatedOrderId())
                .eq(OrderInItem::getProductId, item.getProductId())
                .eq(OrderInItem::getBatchNumber, item.getBatchNumber())
                .set(OrderInItem::getLocation, JsonUtils.toJsonString(dto.getLocations()))
                .update();
        if (!update2) {
            throw new BizException("修改订单详情位置信息失败");
        }
        // 占用库位
        dto.getLocations().forEach(location -> {
            boolean b = locationClient.updateStatusInStorage(location, LocationStatusEnums.OCCUPIED.getCode(),
                    dto.getProductId());
            if (!b) {
                throw new BizException("占用库位信息失败");
            }
        });
        // 修改质检订单状态信息
        boolean update = this.lambdaUpdate().eq(Inspection::getId, item.getInspectionId())
                .set(Inspection::getReceiveStatus, ReceiveStatus.PENDING.getCode()).update();
        if (!update) {
            throw new BizException("修改质检状态信息失败");
        }
        return Result.success(null, "上架成功");
    }

    @Override
    public Result<String> stockAll(String inspectNo) {
        // 获取质检信息
        Inspection one = this.lambdaQuery().eq(Inspection::getInspectionNo, inspectNo).one();
        // 修改质检状态
        boolean update = this.lambdaUpdate().eq(Inspection::getId, one.getId())
                .set(Inspection::getReceiveStatus, ReceiveStatus.DONE.getCode())
                .update();

        // 修改订单状态
        boolean update1 = orderInService.lambdaUpdate()
                .eq(OrderIn::getId, one.getRelatedOrderId())
                .set(OrderIn::getStatus, OrderStatusEnums.COMPLETED.getCode())
                .update();

        // 修改订单详情状态信息
        boolean update2 = orderInItemService.lambdaUpdate()
                .eq(OrderInItem::getOrderId, one.getRelatedOrderId())
                .set(OrderInItem::getStatus, OrderStatusEnums.COMPLETED.getCode())
                .update();

        if (!update || !update1 || !update2) {
            throw new BizException("修改状态失败");
        }
        // 获取质检详情信息
        LambdaQueryWrapper<InspectionItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(InspectionItem::getInspectionId, one.getId())
                .eq(InspectionItem::getReceiveStatus, ReceiveStatus.DONE.getCode())
                .eq(InspectionItem::getQualityStatus, QualityStatusEnums.PASSED);
        List<InspectionItem> inspectionItems = inspectionItemMapper.selectList(wrapper);
        // 库存处理
        inspectionItems.forEach((item) -> {
            // 获取订单详情
            OrderInItem orderItem = orderInItemService.lambdaQuery()
                    .eq(OrderInItem::getOrderId, one.getRelatedOrderId())
                    .eq(OrderInItem::getProductId, item.getProductId())
                    .eq(OrderInItem::getBatchNumber, item.getBatchNumber())
                    .one();
            if (Objects.isNull(orderItem)) {
                throw new BizException("业务异常");
            }
            Assert.equals(item.getProductId(), orderItem.getProductId(), "产品id不一致");
            Assert.equals(item.getBatchNumber(), orderItem.getBatchNumber(), "批次号不一致");
            // 获取库存
            Stock stock = stockClient.getStockByProductIdAndBatch(orderItem.getProductId(), orderItem.getBatchNumber());

            if (Objects.isNull(stock)) {
                // 库存不存在新增库存
                stock = new Stock();
                // 设置字段
                stock.setProductId(orderItem.getProductId());
                stock.setProductCode(orderItem.getProductCode());
                stock.setAreaId(orderItem.getAreaId());
                stock.setLocation(orderItem.getLocation());
                stock.setQuantity(orderItem.getActualQuantity());
                stock.setAvailableQuantity(orderItem.getActualQuantity());
                stock.setAlertStatus(AlertStatusEnums.NORMAL);
                stock.setBatchNumber(orderItem.getBatchNumber());
                stock.setProductionDate(LocalDate.now());
                stock.setUpdateTime(LocalDate.now());
                stock.setCreateTime(LocalDate.now());
                // 新增库存
                boolean b = stockClient.addStock(stock);
                if (!b) {
                    throw new BizException("新增库位信息失败");
                }
            } else {
                // 库存存在更新库存
                stock.setQuantity(stock.getQuantity() + item.getQualifiedQuantity());
                stock.setAvailableQuantity(stock.getAvailableQuantity() + item.getQualifiedQuantity());

                // 判断是否超过最大库存
                if (stock.getQuantity() >= stock.getMaxStock()) {
                    Alert alert = new Alert();
                    alert.setStockId(stock.getId());
                    alert.setAlertNo(idGenerate.generateAlertNo());
                    alert.setCurrentQuantity(stock.getQuantity());
                    alert.setMinStock(stock.getMinStock());
                    alert.setMaxStock(stock.getMaxStock());
                    alert.setAlertType(AlertStatusEnums.HIGH);
                    alert.setAlertTime(LocalDateTime.now());
                    Area area = locationClient.getArea(stock.getAreaId());
                    alert.setHandler(area.getAreaManager());
                    alert.setCreateTime(LocalDateTime.now());
                    alert.setUpdateTime(LocalDateTime.now());
                    boolean b = alertClient.addAlert(alert);
                    // 修改库存状态
                    stock.setAlertStatus(AlertStatusEnums.HIGH);
                    if (!b) {
                        throw new BizException("新增预警信息失败");
                    }

                    // 发送消息通知
                    User to = userClient.getUserById(area.getAreaManager());
                    Msg msg = new Msg(MsgTypeEnums.STOCK_WARNING, "库存预警", "库存超过最大库存", to.getUserId(),
                            to.getRealName(), "-1", "system", MsgPriorityEnums.NORMAL, alert.getAlertNo(),
                            MsgBizEnums.STOCK_WARNING);
                    rabbitTemplate.convertAndSend(MQConstant.EXCHANGE_NAME, MQConstant.ROUTING_KEY,
                            new WsMsgDataVO<>(msg, MsgEnums.NOTICE.getCode(), to.getUserId()));
                }

                // 更新库位信息取并集
                List<Location> itemLocation = item.getLocation();
                List<Location> stockLocation = stock.getLocation();
                List<Location> location = new ArrayList<>();
                Set<String> processedShelfIds = new HashSet<>(); // 标记处理过的货架ID

                // 处理相同货架ID的情况
                for (Location il : itemLocation) {
                    boolean found = false;
                    for (Location sl : stockLocation) {
                        if (Objects.equals(sl.getShelfId(), il.getShelfId())) {
                            found = true;
                            // 创建新的Location对象，合并库位信息
                            Location newLocation = new Location();
                            newLocation.setShelfId(il.getShelfId());

                            // 取二者库位信息的并集
                            Set<String> mergedStorageIds = new HashSet<>();
                            if (il.getStorageIds() != null) {
                                mergedStorageIds.addAll(il.getStorageIds());
                            }
                            if (sl.getStorageIds() != null) {
                                mergedStorageIds.addAll(sl.getStorageIds());
                            }
                            newLocation.setStorageIds(new ArrayList<>(mergedStorageIds));

                            // 添加到结果列表
                            location.add(newLocation);
                            processedShelfIds.add(il.getShelfId());
                            break;
                        }
                    }

                    // 如果itemLocation中的货架在stockLocation中不存在，也要添加
                    if (!found) {
                        location.add(il);
                        processedShelfIds.add(il.getShelfId());
                    }
                }

                // 添加stockLocation中还未处理的货架
                for (Location sl : stockLocation) {
                    if (!processedShelfIds.contains(sl.getShelfId())) {
                        location.add(sl);
                        processedShelfIds.add(sl.getShelfId());
                    }
                }

                // 更新库位信息
                stock.setLocation(location);

                // 更新库存
                stock.setUpdateTime(LocalDate.now());

                // 判断是否正常
                if (stock.getQuantity() < stock.getMaxStock() && stock.getQuantity() > stock.getMinStock()) {
                    stock.setAlertStatus(AlertStatusEnums.LOW);
                }
                boolean b = stockClient.updateStock(stock);
                if (!b) {
                    throw new BizException("更新库位信息失败");
                }
            }
        });

        return Result.success(null, "上架成功");

    }

    /**
     * 修改质检状态
     *
     * @param remark
     * @param inspectionNo
     * @param status
     * @return
     */
    private boolean updateInspectionStatus(String remark, String inspectionNo, QualityStatusEnums status) {
        return this.lambdaUpdate().eq(Inspection::getInspectionNo, inspectionNo)
                .set(Inspection::getStatus, status.getCode())
                .set(Inspection::getInspectionTime, LocalDateTime.now())
                .set(Inspection::getUpdateTime, LocalDateTime.now())
                .set(Inspection::getRemark, remark).update();
    }
}
