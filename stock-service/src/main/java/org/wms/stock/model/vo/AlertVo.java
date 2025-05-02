package org.wms.stock.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.wms.common.model.vo.StockVo;
import org.wms.stock.model.entity.Alert;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlertVo extends Alert {

    private StockVo stock;
}
