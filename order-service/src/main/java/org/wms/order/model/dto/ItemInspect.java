package org.wms.order.model.dto;

import lombok.Data;

@Data
public class ItemInspect {
    private String itemId; // 质检详情id
    private String productId; // 产品id
    private Integer count; // 合格数量
    private Integer actualQuantity; // 实际数量
    private String remark; // 订单详情备注
    private boolean approval; // 通过
}
