package org.wms.order.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.wms.common.model.vo.LocationVo;
import org.wms.order.model.entity.PickingItem;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PickingItemVo extends PickingItem {
    String areaName;
    List<LocationVo> locations;
}
