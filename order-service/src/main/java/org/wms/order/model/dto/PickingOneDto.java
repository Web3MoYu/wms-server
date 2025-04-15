package org.wms.order.model.dto;

import lombok.Data;
import org.wms.common.model.vo.LocationInfo;

import java.util.List;
import java.util.Set;

@Data
public class PickingOneDto {

    String itemId; // 订单详情ID
    List<LocationInfo> location; // 位置信息
    /**
     * 当前库位是否被取消
     */
    Set<String> set;

    Integer count; // 实际数量

    String areaId; // 区域ID

    String remark; // 备注
}
