package org.wms.order.model.dto;

import lombok.Data;
import org.wms.common.model.Location;

import java.util.List;

@Data
public class StockInDto {
    private String itemId; // 订单详情id
    private String productId; // 产品id
    private Integer count; // 上架数量
    private List<Location> locations; // 位置信息
}
