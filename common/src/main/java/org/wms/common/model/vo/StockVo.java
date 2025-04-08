package org.wms.common.model.vo;

import org.wms.common.entity.stock.Stock;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class StockVo extends Stock {

    // 商品名字
    String productName;

    // 区域名称
    String areaName;

    // 位置信息
    List<LocationVo> locationVo;
}
