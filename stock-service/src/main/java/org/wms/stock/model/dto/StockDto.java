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
    /**
     * 是否按数量生序排序
     */
    Boolean ascSortByQuantity;
    /**
     * 是否按可用数量生序排序
     */
    Boolean ascSortByAvailableQuantity;
    /**
     * 是否按锁定数量生序排序
     */
    Boolean ascSortByLockedQuantity;
}
