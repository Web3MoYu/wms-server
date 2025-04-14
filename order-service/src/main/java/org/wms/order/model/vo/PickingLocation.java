package org.wms.order.model.vo;

import lombok.Data;
import org.wms.common.entity.location.Area;
import org.wms.common.model.vo.LocationInfo;

import java.util.List;

@Data
public class PickingLocation {
    String itemId; // 拣货详情ID
    Area area; // 区域信息
    List<LocationInfo> locations; // 库位信息
}
