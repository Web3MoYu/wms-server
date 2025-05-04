package org.wms.stock.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.wms.common.entity.location.Area;
import org.wms.common.entity.sys.User;
import org.wms.common.model.vo.LocationVo;
import org.wms.common.model.vo.StockVo;
import org.wms.stock.model.entity.Movement;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class MovementVo extends Movement {

    private StockVo stock;

    private Area beforeArea;

    private List<LocationVo> beforeLocationVo;

    private Area afterArea;

    private List<LocationVo> afterLocationVo;

    private User opeatorUser;

    private User approverUser;
}
