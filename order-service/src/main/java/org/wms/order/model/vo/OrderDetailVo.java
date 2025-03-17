package org.wms.order.model.vo;

import lombok.Data;
import org.wms.common.entity.product.Product;

@Data
public class OrderDetailVo<T> {

    private Product product;

    private T orderItems;
}
