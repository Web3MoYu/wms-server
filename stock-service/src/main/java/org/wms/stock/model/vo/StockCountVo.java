package org.wms.stock.model.vo;

import lombok.Data;
import org.wms.common.model.vo.CountVo;

import java.util.List;

@Data
public class StockCountVo {

    private String name;

    private List<CountVo> batchCount;
}
