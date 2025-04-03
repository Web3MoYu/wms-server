package org.wms.order.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.wms.api.client.LocationClient;
import org.wms.api.client.ProductClient;
import org.wms.api.client.UserClient;
import org.wms.common.constant.MQConstant;
import org.wms.common.entity.msg.Msg;
import org.wms.common.entity.msg.WsMsgDataVO;
import org.wms.common.entity.product.Product;
import org.wms.common.entity.sys.User;
import org.wms.common.enums.inspect.InspectType;
import org.wms.common.enums.location.LocationStatusEnums;
import org.wms.common.enums.msg.MsgBizEnums;
import org.wms.common.enums.msg.MsgEnums;
import org.wms.common.enums.msg.MsgPriorityEnums;
import org.wms.common.enums.msg.MsgTypeEnums;
import org.wms.common.enums.order.OrderType;
import org.wms.common.exception.BizException;
import org.wms.common.model.Location;
import org.wms.common.model.Result;
import org.wms.common.model.vo.LocationVo;
import org.wms.common.utils.IdGenerate;
import org.wms.common.utils.JsonUtils;
import org.wms.order.mapper.InspectionItemMapper;
import org.wms.order.mapper.InspectionMapper;
import org.wms.order.mapper.OrderInItemMapper;
import org.wms.order.mapper.OrderInMapper;
import org.wms.order.model.dto.ApprovalDto;
import org.wms.order.model.dto.OrderDto;
import org.wms.order.model.entity.Inspection;
import org.wms.common.entity.order.InspectionItem;
import org.wms.order.model.entity.OrderIn;
import org.wms.order.model.entity.OrderInItem;
import org.wms.order.model.enums.OrderInType;
import org.wms.order.model.enums.OrderStatusEnums;
import org.wms.order.model.enums.QualityStatusEnums;
import org.wms.order.model.vo.OrderDetailVo;
import org.wms.order.service.OrderInService;
import org.wms.security.util.SecurityUtil;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;

/**
 * @author moyu
 * @description 针对表【order_in(入库订单表)】的数据库操作Service实现
 * @createDate 2025-03-12 20:41:27
 */
@Service
public class OrderInServiceImpl extends ServiceImpl<OrderInMapper, OrderIn>
        implements OrderInService {

    @Resource
    IdGenerate idGenerate;

    @Resource
    ProductClient productClient;

    @Resource
    RabbitTemplate rabbitTemplate;

    @Resource
    UserClient userClient;

    @Resource
    OrderInItemMapper orderInItemMapper;

    @Resource
    LocationClient locationClient;

    @Resource
    OrderInMapper orderInMapper;

    @Resource
    InspectionMapper inspectionMapper;

    @Resource
    InspectionItemMapper inspectionItemMapper;

    @Override
    public Result<String> addOrder(OrderDto<OrderIn, OrderInItem> order) {
        String userID = SecurityUtil.getUserID();
        // 设置订单属性
        OrderIn orderIn = order.getOrder();
        orderIn.setCreator(userID);
        orderIn.setOrderNo(idGenerate.generateOrderNo(orderIn.getType()));
        orderIn.setType(OrderType.IN_ORDER);
        orderIn.setOrderType(OrderInType.PURCHASE);
        orderIn.setStatus(OrderStatusEnums.PENDING_REVIEW);
        orderIn.setQualityStatus(QualityStatusEnums.NOT_INSPECTED);
        orderIn.setCreateTime(LocalDateTime.now());
        orderIn.setUpdateTime(LocalDateTime.now());
        // 插入订单
        boolean insert = this.save(orderIn);
        if (!insert) {
            throw new BizException(303, "插入订单失败");
        }
        List<OrderInItem> orderItem = order.getOrderItems();
        // 设置入库订单详细信息
        orderItem.forEach((item) -> {
            item.setOrderId(orderIn.getId());
            item.setStatus(OrderStatusEnums.PENDING_REVIEW);
            item.setQualityStatus(QualityStatusEnums.NOT_INSPECTED);
            item.setCreateTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
        });
        // 更新商品信息
        orderItem.forEach((item) -> {
            String productCode = item.getProductCode();
            String productId = item.getProductId();
            Product productById = productClient.getProductById(productId);
            if (StrUtil.isEmpty(productId)) {
                long id = IdWorker.getId();
                Product product = order.getProducts().get(productCode);
                product.setId(String.valueOf(id));
                product.setUpdateTime(LocalDateTime.now());
                product.setCreateTime(LocalDateTime.now());
                productClient.createProduct(product);
                productById = product;
            }
            item.setProductId(productById.getId());
        });
        // 插入订单详情
        orderInItemMapper.insert(orderItem, orderItem.size());
        // 构建消息
        User from = userClient.getUserById(orderIn.getCreator());
        User to = userClient.getUserById(orderIn.getApprover());
        Msg msg = new Msg(MsgTypeEnums.ORDER_STATUS, "订单通知", "你有一笔订单需要审批", to.getUserId(),
                to.getRealName(), from.getUserId(), from.getRealName(), MsgPriorityEnums.NORMAL, orderIn.getOrderNo(),
                MsgBizEnums.INBOUND_ORDER);
        rabbitTemplate.convertAndSend(MQConstant.EXCHANGE_NAME, MQConstant.ROUTING_KEY,
                new WsMsgDataVO<>(msg, MsgEnums.NOTICE.getCode(), to.getUserId()));
        return Result.success(null, "插入成功");
    }

    @Override
    public Result<List<OrderDetailVo<OrderInItem>>> inDetail(String id) {
        // 获取items
        LambdaQueryWrapper<OrderInItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderInItem::getOrderId, id);
        List<OrderInItem> orderInItems = orderInItemMapper.selectList(wrapper);
        // 封装vo
        List<OrderDetailVo<OrderInItem>> collect = orderInItems.stream().map((item) -> {
            OrderDetailVo<OrderInItem> vo = new OrderDetailVo<>();
            vo.setOrderItems(item);
            Product product = productClient.getProductById(item.getProductId());
            vo.setProduct(product);
            if (StringUtils.hasLength(item.getAreaId())) {
                String areaName = locationClient.getArea(item.getAreaId()).getAreaName();
                vo.setAreaName(areaName);
            }
            if (Objects.nonNull(item.getLocation()) && !item.getLocation().isEmpty()) {
                Set<LocationVo> set = item.getLocation().stream().map(it -> locationClient.getLocations(it))
                        .collect(Collectors.toSet());
                vo.setLocationName(set);
            }
            return vo;
        }).toList();
        return Result.success(collect, "查询成功");
    }

    @Override
    public void updateStatus(String id, String remark, OrderStatusEnums statusEnums) {
        // 入库订单
        LambdaUpdateWrapper<OrderIn> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(OrderIn::getId, id)
                .set(OrderIn::getStatus, statusEnums.getCode())
                .set(OrderIn::getRemark, remark)
                .set(OrderIn::getUpdateTime, LocalDateTime.now());
        boolean update = this.update(wrapper);
        // 订单详情
        LambdaUpdateWrapper<OrderInItem> itemWrapper = new LambdaUpdateWrapper<>();
        itemWrapper.eq(OrderInItem::getOrderId, id)
                .set(OrderInItem::getStatus, statusEnums.getCode())
                .set(OrderInItem::getRemark, remark)
                .set(OrderInItem::getUpdateTime, LocalDateTime.now());
        int update1 = orderInItemMapper.update(itemWrapper);
        if (!update || update1 <= 0) {
            throw new BizException(303, "失败");
        }
    }

    @Override
    public Result<String> approve(String id, List<ApprovalDto> dto, String inspector) {
        dto.forEach((item) -> {
            String areaId = item.getAreaId();
            List<Location> location = item.getLocation();
            String detailId = item.getId();
            // 修改订单详情的区域id和位置
            LambdaUpdateWrapper<OrderInItem> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(OrderInItem::getId, detailId)
                    .set(OrderInItem::getAreaId, areaId)
                    .set(OrderInItem::getLocation, JsonUtils.toJsonString(location))
                    .set(OrderInItem::getUpdateTime, LocalDateTime.now());
            int update = orderInItemMapper.update(wrapper);
            if (update <= 0) {
                throw new BizException(303, "审批失败");
            }
            // 修改库位状态
            location.forEach((i) -> {
                boolean b = locationClient.updateStatusInStorage(i,
                        LocationStatusEnums.OCCUPIED.getCode(), item.getProductId());
                if (!b) {
                    throw new BizException(303, "审批失败");
                }
            });
        });
        LambdaUpdateWrapper<OrderIn> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(OrderIn::getId, id)
                .set(OrderIn::getInspector, inspector);
        int update = orderInMapper.update(wrapper);
        if (update <= 0) {
            throw new BizException(303, "审批失败");
        }
        // 修改订单状态和详情状态
        updateStatus(id, "审批通过",
                OrderStatusEnums.APPROVED);
        return Result.success(null, "审批成功");
    }

    @Override
    public Result<String> receiveGoods(String id) {
        // 增加质检信息
        OrderIn orderIn = this.lambdaQuery().eq(OrderIn::getId, id).one();
        LambdaUpdateWrapper<OrderInItem> itemWrapper = new LambdaUpdateWrapper<>();
        itemWrapper.eq(OrderInItem::getOrderId, id);
        List<OrderInItem> inItems = orderInItemMapper.selectList(itemWrapper);
        // 生成质检信息
        Inspection inspection = new Inspection();
        inspection.setInspectionNo(idGenerate.generateInspectionNo(InspectType.INBOUND_INSPECT));
        inspection.setInspectionType(InspectType.INBOUND_INSPECT);
        inspection.setRelatedOrderId(orderIn.getId());
        inspection.setRelatedOrderNo(orderIn.getOrderNo());
        inspection.setInspector(orderIn.getInspector());
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
            inspectionItem.setAreaId(item.getAreaId());
            inspectionItem.setLocation(item.getLocation());
            inspectionItem.setInspectionQuantity(item.getExpectedQuantity());
            inspectionItem.setCreateTime(LocalDateTime.now());
            inspectionItem.setUpdateTime(LocalDateTime.now());
            return inspectionItem;
        }).toList();
        // 插入质检详情信息
        inspectionItemMapper.insert(list);

        // 修改实际到达时间
        boolean update = this.lambdaUpdate().eq(OrderIn::getId, id)
                .set(OrderIn::getActualTime, LocalDateTime.now()).update();
        if (!update) {
            throw new BizException(303, "修改到达时间失败");
        }

        // 生成消息
        User from = userClient.getUserById(orderIn.getApprover());
        User to = userClient.getUserById(orderIn.getInspector());
        Msg msg = new Msg(MsgTypeEnums.QUALITY_CHECK, "质检通知", "你有一笔质检订单", to.getUserId(),
                to.getRealName(), from.getUserId(), from.getRealName(), MsgPriorityEnums.NORMAL, inspection.getInspectionNo(),
                MsgBizEnums.QUALITY_CHECK);
        rabbitTemplate.convertAndSend(MQConstant.EXCHANGE_NAME, MQConstant.ROUTING_KEY,
                new WsMsgDataVO<>(msg, MsgEnums.NOTICE.getCode(), to.getUserId()));
        return Result.success(null, "收货成功");
    }
}




