package org.wms.stock.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.wms.common.entity.location.Area;
import org.wms.common.entity.sys.User;
import org.wms.stock.model.entity.Check;

@Data
@EqualsAndHashCode(callSuper = false)
public class CheckVo extends Check {

    private Area area;

    private User creatorUser;

    private User checkerUser;
}
