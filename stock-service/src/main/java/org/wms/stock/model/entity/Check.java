package org.wms.stock.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.wms.stock.model.enums.CheckStatus;

/**
 * 盘点单表
 *
 * @TableName stock_check
 */
@TableName(value = "stock_check")
@Data
public class Check {
    /**
     * 盘点ID
     */
    @TableId
    private String id;

    /**
     * 盘点单号
     */
    private String checkNo;

    /**
     * 区域ID
     */
    private String areaId;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 盘点人
     */
    private String checker;

    /**
     * 计划开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime planStartTime;

    /**
     * 计划结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime planEndTime;

    /**
     * 实际开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime actualStartTime;

    /**
     * 实际结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime actualEndTime;

    /**
     * 状态：0-待盘点,1-待确认，2-已完成
     */
    private CheckStatus status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

}