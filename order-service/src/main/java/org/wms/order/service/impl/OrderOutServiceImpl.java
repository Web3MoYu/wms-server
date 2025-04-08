package org.wms.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.util.StringUtils;
import org.wms.api.client.LocationClient;
import org.wms.api.client.ProductClient;
import org.wms.api.client.UserClient;
import org.wms.common.constant.MQConstant;
import org.wms.common.entity.msg.Msg;
import org.wms.common.entity.msg.WsMsgDataVO;
import org.wms.common.entity.product.Product;
import org.wms.common.entity.sys.User;
import org.wms.common.enums.msg.MsgBizEnums;
import org.wms.common.enums.msg.MsgEnums;
import org.wms.common.enums.msg.MsgPriorityEnums;
import org.wms.common.enums.msg.MsgTypeEnums;
import org.wms.common.enums.order.OrderType;
import org.wms.common.exception.BizException;
import org.wms.common.model.Result;
import org.wms.common.model.vo.LocationVo;
import org.wms.common.utils.IdGenerate;
import org.wms.order.mapper.OrderOutItemMapper;
import org.wms.order.model.dto.OrderDto;
import org.wms.order.model.entity.OrderOut;
import org.wms.order.model.entity.OrderOutItem;
import org.wms.order.model.enums.OrderStatusEnums;
import org.wms.order.model.enums.QualityStatusEnums;
import org.wms.order.model.vo.OrderDetailVo;
import org.wms.order.service.OrderOutService;
import org.wms.order.mapper.OrderOutMapper;
import org.springframework.stereotype.Service;
import org.wms.security.util.SecurityUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author moyu
 * @description 针对表【order_out(出库订单表)】的数据库操作Service实现
 * @createDate 2025-03-12 20:41:27
 */
@Service
public class OrderOutServiceImpl extends ServiceImpl<OrderOutMapper, OrderOut>
        implements OrderOutService {

    @Resource
    RabbitTemplate rabbitTemplate;

    @Resource
    OrderOutItemMapper orderOutItemMapper;

    @Resource
    ProductClient productClient;

    @Resource
    UserClient userClient;

    @Resource
    LocationClient locationClient;

    @Resource
    IdGenerate idGenerate;

    @Override
    public Result<List<OrderDetailVo<OrderOutItem>>> outDetail(String id) {
        // 获取items
        LambdaQueryWrapper<OrderOutItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderOutItem::getOrderId, id);
        List<OrderOutItem> orderOutItems = orderOutItemMapper.selectList(wrapper);
        // 封装vo
        List<OrderDetailVo<OrderOutItem>> collect = orderOutItems.stream().map((item) -> {
            OrderDetailVo<OrderOutItem> vo = new OrderDetailVo<>();
            vo.setOrderItems(item);
            Product product = productClient.getProductById(item.getProductId());
            if (StringUtils.hasLength(item.getAreaId())) {
                String areaName = locationClient.getArea(item.getAreaId()).getAreaName();
                vo.setAreaName(areaName);
            }
            if (Objects.nonNull(item.getLocation()) && !item.getLocation().isEmpty()) {
                Set<LocationVo> set = item.getLocation().stream().map(it -> locationClient.getLocations(it))
                        .collect(Collectors.toSet());
                vo.setLocationName(set);
            }
            vo.setProduct(product);
            return vo;
        }).toList();
        return Result.success(collect, "查询成功");
    }

    @Override
    public void updateStatus(String id, String remark, OrderStatusEnums statusEnums) {
        // 出库订单
        LambdaUpdateWrapper<OrderOut> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(OrderOut::getId, id)
                .set(OrderOut::getStatus, statusEnums.getCode())
                .set(OrderOut::getRemark, remark)
                .set(OrderOut::getUpdateTime, LocalDateTime.now());
        boolean update = this.update(wrapper);
        LambdaUpdateWrapper<OrderOutItem> itemWrapper = new LambdaUpdateWrapper<>();
        itemWrapper.eq(OrderOutItem::getOrderId, id)
                .set(OrderOutItem::getStatus, statusEnums.getCode())
                .set(OrderOutItem::getRemark, remark)
                .set(OrderOutItem::getUpdateTime, LocalDateTime.now());
        int update1 = orderOutItemMapper.update(itemWrapper);
        if (!update || update1 <= 0) {
            throw new BizException(303, "失败");
        }
    }

    @Override
    public Result<String> addOrder(OrderDto<OrderOut, OrderOutItem> order) {
        String userid = SecurityUtil.getUserID();
        // 添加出库订单
        OrderOut orderOut = order.getOrder();
        orderOut.setOrderNo(idGenerate.generateOrderNo(OrderType.OUT_ORDER));
        orderOut.setType(OrderType.OUT_ORDER);
        orderOut.setStatus(OrderStatusEnums.PENDING_REVIEW);
        orderOut.setQualityStatus(QualityStatusEnums.NOT_INSPECTED);
        orderOut.setCreator(userid);
        orderOut.setCreateTime(LocalDateTime.now());
        orderOut.setUpdateTime(LocalDateTime.now());
        boolean save = this.save(orderOut);
        if (!save) {
            throw new BizException(303, "出库订单添加失败");
        }
        // 添加出库订单详情
        List<OrderOutItem> items = order.getOrderItems();
        items.forEach((item) -> {
            // 查询产品信息
            Product product = productClient.getProductById(item.getProductId());
            item.setOrderId(orderOut.getId());
            item.setProductName(product.getProductName());
            item.setProductCode(product.getProductCode());
            item.setAmount(item.getPrice().multiply(new BigDecimal(item.getExpectedQuantity())));
            item.setStatus(OrderStatusEnums.PENDING_REVIEW);
            item.setQualityStatus(QualityStatusEnums.NOT_INSPECTED);
            item.setUpdateTime(LocalDateTime.now());
            item.setCreateTime(LocalDateTime.now());
        });
        orderOutItemMapper.insert(items, items.size());
        // 构建消息
        User from = userClient.getUserById(orderOut.getCreator());
        User to = userClient.getUserById(orderOut.getApprover());
        Msg msg = new Msg(MsgTypeEnums.ORDER_STATUS, "订单通知", "你有一笔出库订单需要审批", to.getUserId(),
                to.getRealName(), from.getUserId(), from.getRealName(), MsgPriorityEnums.NORMAL, orderOut.getOrderNo(),
                MsgBizEnums.OUTBOUND_ORDER);
        rabbitTemplate.convertAndSend(MQConstant.EXCHANGE_NAME, MQConstant.ROUTING_KEY,
                new WsMsgDataVO<>(msg, MsgEnums.NOTICE.getCode(), to.getUserId()));
        return Result.success(null, "插入成功");
    }
}




