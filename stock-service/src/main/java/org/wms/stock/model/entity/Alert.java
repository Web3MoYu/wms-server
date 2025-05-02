package org.wms.stock.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.wms.common.enums.stock.AlertStatusEnums;

/**
 * 产品库存预警记录表
 *
 * @TableName stock_alert
 */
@TableName(value = "stock_alert")
@Data
public class Alert {
    /**
     * 记录ID
     */
    @TableId
    private String id;

    /**
     * 库存ID
     */
    private String stockId;

    /**
     * 当前数量
     */
    private Integer currentQuantity;

    /**
     * 最小库存
     */
    private Integer minStock;

    /**
     * 最大库存
     */
    private Integer maxStock;

    /**
     * 预警状态：0-正常，1-低于最小库存，2-超过最大库存
     */
    private AlertStatusEnums alertType;

    /**
     * 预警时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime alertTime;

    /**
     * 是否处理：0-否，1-是
     */
    private Integer isHandled;

    /**
     * 处理人
     */
    private String handler;

    /**
     * 处理时间
     */
    private LocalDateTime handlingTime;

    /**
     * 处理方法
     */
    private String handlingMethod;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}