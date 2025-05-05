package org.wms.stock.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.wms.common.model.vo.StockVo;
import org.wms.stock.model.entity.CheckItem;

@Data
@EqualsAndHashCode(callSuper = false)
public class CheckItemVo extends CheckItem {

    StockVo stock;
}
