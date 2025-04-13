package org.wms.order.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.wms.common.entity.sys.User;
import org.wms.order.model.entity.PickingOrder;

@EqualsAndHashCode(callSuper = true)
@Data
public class PickingOrderVo extends PickingOrder {

    private User pickingUser;
}
