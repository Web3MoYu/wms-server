package org.wms.stock.model.dto;

import lombok.Data;

@Data
public class StockDto {

    /**
     * 当前页码
     */
    Integer page;
    /**
     * 每页条数
     */
    Integer pageSize;
    /**
     * 商品ID
     */
    String productId;
    /**
     * 区域ID
     */
    String areaId;
    /**
     * 状态
     */
    Integer status;
    /**
     * 批次号
     */
    String batchNumber;
    /**
     * 是否按生产日期升序排序
     */
    Boolean ascSortByProdDate;
}
