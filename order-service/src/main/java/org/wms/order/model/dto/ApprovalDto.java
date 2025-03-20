package org.wms.order.model.dto;

import lombok.Data;
import org.wms.common.model.Location;

import java.util.List;

@Data
public class ApprovalDto {
    private String id; // 详情id
    private String areaId; // 区域id
    private String productId; // 产品id
    private List<Location> location; // 位置
}
