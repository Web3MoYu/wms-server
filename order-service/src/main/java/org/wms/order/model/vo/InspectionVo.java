package org.wms.order.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.wms.common.entity.sys.User;
import org.wms.order.model.entity.Inspection;

@Data
@EqualsAndHashCode(callSuper = true)
public class InspectionVo extends Inspection {

    User inspectorInfo;

}
