package org.wms.order.model.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderStatisticsVo {
    Integer status;
    String statusVo;
    BigDecimal totalAmount;
    Integer totalQuantity;
}
