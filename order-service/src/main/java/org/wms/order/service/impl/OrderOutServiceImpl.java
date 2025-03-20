package org.wms.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.wms.api.client.ProductClient;
import org.wms.common.entity.product.Product;
import org.wms.common.exception.BizException;
import org.wms.common.model.Result;
import org.wms.order.mapper.OrderOutItemMapper;
import org.wms.order.model.entity.OrderOut;
import org.wms.order.model.entity.OrderOutItem;
import org.wms.order.model.enums.OrderStatusEnums;
import org.wms.order.model.vo.OrderDetailVo;
import org.wms.order.service.OrderOutService;
import org.wms.order.mapper.OrderOutMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author moyu
 * @description 针对表【order_out(出库订单表)】的数据库操作Service实现
 * @createDate 2025-03-12 20:41:27
 */
@Service
public class OrderOutServiceImpl extends ServiceImpl<OrderOutMapper, OrderOut>
        implements OrderOutService {

    @Resource
    OrderOutItemMapper orderOutItemMapper;

    @Resource
    ProductClient productClient;

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
            vo.setProduct(product);
            return vo;
        }).toList();
        return Result.success(collect, "查询成功");
    }

    @Override
    public void updateStatus(Integer type, String id, String remark, OrderStatusEnums statusEnums) {
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
}




