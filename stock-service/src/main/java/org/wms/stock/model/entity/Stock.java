package org.wms.stock.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.wms.common.handler.StockLocationTypeHandler;
import org.wms.common.model.Location;
import org.wms.stock.model.enums.AlertStatusEnums;

/**
 * 库存表
 *
 * @TableName stock
 */
@TableName(value = "stock")
@Data
public class Stock {
    /**
     * 库存ID
     */
    @TableId
    private String id;

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 产品编码
     */
    private String productCode;

    /**
     * 区域ID
     */
    private String areaId;

    /**
     * 具体位置，格式
     * [
     * {
     * shelfId:,
     * storageId:[]
     * }
     * ]
     */
    @TableField(typeHandler = StockLocationTypeHandler.class)
    private List<Location> location;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 可用数量
     */
    private Integer availableQuantity;

    /**
     * 锁定数量
     */
    private Integer lockedQuantity;

    /**
     * 预警状态：0-正常，1-低于最小库存，2-超过最大库存
     */
    private AlertStatusEnums alertStatus;

    /**
     * 批次号
     */
    private String batchNumber;

    /**
     * 生产日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime productionDate;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime updateTime;

}