package org.wms.order.model.dto;

import lombok.Data;
import org.wms.common.entity.product.Product;

import java.util.List;
import java.util.Map;

@Data
public class OrderDto<O, OI> {
    // 订单
    O order;

    // 订单详情
    List<OI> orderItems;

    Map<String, Product> products;
}
