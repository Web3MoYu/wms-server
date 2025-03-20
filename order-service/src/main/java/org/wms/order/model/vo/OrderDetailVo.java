package org.wms.order.model.vo;

import lombok.Data;
import org.wms.common.entity.product.Product;
import org.wms.common.model.vo.LocationVo;

import java.util.Set;

@Data
public class OrderDetailVo<T> {

    private Product product;

    private T orderItems;

    private String areaName;

    private Set<LocationVo> locationName;

}
