package org.wms.order.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class InBoundInspectDto {
    private List<ItemInspect> itemInspects;
    private String remark; // 订单备注
    private String inspectionNo; // 质检编号
}
