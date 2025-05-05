package org.wms.stock.model.dto;

import lombok.Data;

@Data
public class StockCheckDto {
    private String checkItemId; // 盘点明细ID
    private Integer actualQuantity; // 实际数量
}
