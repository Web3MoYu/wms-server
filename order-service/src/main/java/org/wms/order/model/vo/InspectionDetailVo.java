package org.wms.order.model.vo;

import lombok.Data;
import org.wms.common.entity.order.InspectionItem;

import java.util.List;

@Data
public class InspectionDetailVo<T> {
    private List<OrderDetailVo<T>> orderDetail;
    private List<InspectionItem> inspectionItems;
}
