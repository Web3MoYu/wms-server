package org.wms.order.model.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderStatisticsVo {
    Integer status; // 订单状态
    String statusVo; // 状态描述
    Integer count; // 订单数
    BigDecimal totalAmount; // 订单总金额
    Integer totalQuantity; // 总商品数量
}
