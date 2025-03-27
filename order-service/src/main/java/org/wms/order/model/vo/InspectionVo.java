package org.wms.order.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.wms.common.entity.sys.User;
import org.wms.order.model.entity.Inspection;
import org.wms.order.model.enums.OrderStatusEnums;

@Data
@EqualsAndHashCode(callSuper = true)
public class InspectionVo extends Inspection {

    private User inspectorInfo;

    private OrderStatusEnums orderStatus; //状态：0-待审核，1-审批通过，2-入库中/出库中，3-已完成，-1-已取消,-2-审批拒绝

}
