package org.wms.order.service.impl;

import java.time.LocalDateTime;
import java.util.*;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.wms.api.client.ProductClient;
import org.wms.api.client.StockClient;
import org.wms.api.client.UserClient;
import org.wms.common.entity.product.Product;
import org.wms.common.entity.sys.User;
import org.wms.common.enums.order.OrderType;
import org.wms.common.exception.BizException;
import org.wms.common.model.Result;
import org.wms.common.utils.IdGenerate;
import org.wms.order.mapper.*;
import org.wms.order.model.dto.OrderDto;
import org.wms.order.model.dto.OrderQueryDto;
import org.wms.order.model.entity.OrderIn;
import org.wms.order.model.entity.OrderInItem;
import org.wms.order.model.entity.OrderOut;
import org.wms.order.model.entity.OrderOutItem;
import org.wms.order.model.enums.OrderInType;
import org.wms.order.model.enums.OrderItemStatus;
import org.wms.order.model.enums.OrderStatusEnums;
import org.wms.order.model.enums.QualityStatusEnums;
import org.wms.order.model.vo.OrderVo;
import org.wms.order.service.OrderService;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import jakarta.annotation.Resource;
import org.wms.security.util.SecurityUtil;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    UserClient userClient;

    @Resource
    OrderMapper orderMapper;

    @Resource
    OrderInMapper orderInMapper;

    @Resource
    OrderInItemMapper orderInItemMapper;

    @Resource
    ProductClient productClient;

    @Resource
    StockClient stockClient;

    @Resource
    OrderOutMapper orderOutMapper;

    @Resource
    OrderOutItemMapper orderOutItemMapper;

    @Resource
    IdGenerate idGenerate;

    @Override
    public Result<Page<OrderVo>> pageOrder(OrderQueryDto queryDto) {
        // 参数校验
        if (queryDto == null) {
            queryDto = new OrderQueryDto();
        }

        // 计算偏移量
        int offset = (queryDto.getPage() - 1) * queryDto.getPageSize();
        int limit = queryDto.getPageSize();

        // 执行联合查询
        List<OrderVo> orders = orderMapper.unionQueryOrders(queryDto, offset, limit);

        // 统计总记录数
        Long total = orderMapper.countUnionQueryOrders(queryDto);

        // 收集所有需要查询的用户ID
        Set<String> userIdSet = new HashSet<>();
        for (OrderVo order : orders) {
            // 收集创建人ID
            if (order.getCreator() != null && order.getCreator().getUserId() != null) {
                userIdSet.add(order.getCreator().getUserId());
            }

            // 收集审批人ID
            if (order.getApprover() != null && order.getApprover().getUserId() != null) {
                userIdSet.add(order.getApprover().getUserId());
            }

            // 收集质检人员ID
            if (order.getInspector() != null && order.getInspector().getUserId() != null) {
                userIdSet.add(order.getInspector().getUserId());
            }
        }

        // 批量查询用户信息
        Map<String, User> userMap = new HashMap<>();
        if (!userIdSet.isEmpty()) {
            try {
                // 批量获取用户信息，减少网络调用
                List<User> userList = userClient.getUserByIds(new ArrayList<>(userIdSet));
                if (userList != null) {
                    for (User user : userList) {
                        if (user != null && user.getUserId() != null) {
                            userMap.put(user.getUserId(), user);
                        }
                    }
                }
            } catch (Exception e) {
                log.error("批量获取用户信息失败", e);
            }
        }

        // 遍历订单列表，为每个订单设置完整的用户信息
        for (OrderVo order : orders) {
            // 填充创建人信息
            if (order.getCreator() != null && order.getCreator().getUserId() != null) {
                String userId = order.getCreator().getUserId();
                User user = userMap.get(userId);
                if (user != null) {
                    order.setCreator(user);
                }
            }

            // 填充审批人信息
            if (order.getApprover() != null && order.getApprover().getUserId() != null) {
                String userId = order.getApprover().getUserId();
                User user = userMap.get(userId);
                if (user != null) {
                    order.setApprover(user);
                }
            }

            // 填充质检人员信息
            if (order.getInspector() != null && order.getInspector().getUserId() != null) {
                String userId = order.getInspector().getUserId();
                User user = userMap.get(userId);
                if (user != null) {
                    order.setInspector(user);
                }
            }
        }

        // 构建分页结果
        Page<OrderVo> page = new Page<>(queryDto.getPage(), queryDto.getPageSize(), total);
        page.setRecords(orders);

        return Result.success(page, "查询成功");
    }

    @Override
    public Result<String> addOrderIn(OrderDto<OrderIn, OrderInItem> order) {
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
        int insert = orderInMapper.insert(orderIn);
        if (insert <= 0) {
            throw new BizException(303, "插入订单失败");
        }
        List<OrderInItem> orderItem = order.getOrderItems();
        // 设置入库订单详细信息
        orderItem.forEach((item) -> {
            item.setOrderId(orderIn.getId());
            item.setStatus(OrderItemStatus.NOT_STARTED);
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
                Product product = order.getProducts().get(productCode);
                product.setUpdateTime(LocalDateTime.now());
                product.setCreateTime(LocalDateTime.now());
                productClient.createProduct(product);
                productById = product;
            }
            item.setProductId(productById.getId());
            // TODO 库存信息的修改是在质检员审核完毕之后才修改的
//            Stock stock = stockClient.checkStockByCodeAndBatch(productCode, batchNumber);
//            if (Objects.isNull(stock)) {
//                stock = new Stock();
//                stock.setProductId(productById.getId());
//                stock.setProductCode(productCode);
//                stock.setQuantity(item.getActualQuantity());
//                stock.setAvailableQuantity(item.getActualQuantity());
//                if (stock.getQuantity() < productById.getMinStock()) {
//                    stock.setAlertStatus(AlertStatusEnums.LOW);
//                }
//                if (stock.getQuantity() > productById.getMaxStock()) {
//                    stock.setAlertStatus(AlertStatusEnums.HIGH);
//                }
//                stock.setBatchNumber(batchNumber);
//                stock.setProductionDate(item.getProductionDate());
//                stock.setCreateTime(LocalDate.now());
//                stock.setUpdateTime(LocalDate.now());
//                boolean b = stockClient.addStock(stock);
//                if (!b) {
//                    throw new BizException("添加库存失败");
//                }
//            } else {
//                stock.setUpdateTime(LocalDate.now());
//                stock.setQuantity(stock.getQuantity() + item.getActualQuantity());
//                stock.setAvailableQuantity(stock.getAvailableQuantity() + item.getActualQuantity());
//                if (stock.getQuantity() < productById.getMinStock()) {
//                    stock.setAlertStatus(AlertStatusEnums.LOW);
//                }
//                if (stock.getQuantity() > productById.getMaxStock()) {
//                    stock.setAlertStatus(AlertStatusEnums.HIGH);
//                }
//                boolean b = stockClient.updateStock(stock);
//                if (!b) {
//                    throw new BizException("更新库存失败");
//                }
//            }
        });
        // 插入订单详情
        orderInItemMapper.insert(orderItem, orderItem.size());
        // TODO 发消息提醒

        return Result.success(null, "插入成功");
    }

    @Override
    public Result<String> addOrderOut(OrderDto<OrderOut, OrderOutItem> order) {
        return null;
    }
}
