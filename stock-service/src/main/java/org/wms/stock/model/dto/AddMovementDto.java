package org.wms.stock.model.dto;

import lombok.Data;
import org.wms.common.model.Location;

import java.util.List;

@Data
public class AddMovementDto {
    String stockId; // 变更的库存ID
    String areaId; // 变更后的区域ID
    List<Location> locations; // 变更的位置信息
    String remark; // 备注
}
