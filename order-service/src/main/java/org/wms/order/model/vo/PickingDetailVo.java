package org.wms.order.model.vo;

import lombok.Data;
import org.wms.order.model.entity.OrderOut;
import org.wms.order.model.entity.OrderOutItem;

import java.util.List;

@Data
public class PickingDetailVo {
    OrderOut order; // 出库订单
    List<OrderDetailVo<OrderOutItem>> orderInfo; //  出库订单详情
    List<PickingItemVo> pickingItems; // 分拣详情
}
