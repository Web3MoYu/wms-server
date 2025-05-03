package org.wms.stock.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.wms.common.entity.sys.User;
import org.wms.common.model.vo.StockVo;
import org.wms.common.entity.stock.Alert;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlertVo extends Alert {

    private StockVo stock;

    private User handlerUser;
}
