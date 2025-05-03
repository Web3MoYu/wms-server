package org.wms.stock.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.wms.common.model.Location;
import org.wms.stock.model.enums.MovementStatus;

/**
 * 库位变动记录表
 *
 * @TableName stock_movement
 */
@TableName(value = "stock_movement")
@Data
public class Movement {
    /**
     * 记录ID
     */
    @TableId
    private String id;

    /**
     * 变动编号
     */
    private String movementNo;

    /**
     * 库存ID
     */
    private String stockId;

    /**
     * 变动前区域ID
     */
    private String beforeAreaId;

    /**
     * 变动前具体位置，格式
     * [
     * {
     * shelfId:,
     * storageIds:[]
     * }
     * ]
     */
    @TableField(typeHandler = org.wms.common.handler.LocationTypeHandler.class)
    private List<Location> beforeLocation;

    /**
     * 变动后区域ID
     */
    private String afterAreaId;

    /**
     * 变动后具体位置，格式
     * [
     * {
     * shelfId:,
     * storageIds:[]
     * }
     * ]
     */
    @TableField(typeHandler = org.wms.common.handler.LocationTypeHandler.class)
    private List<Location> afterLocation;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 审批人
     */
    private String approver;

    /**
     * 变动时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime movementTime;

    /**
     * 拒绝原因
     */
    private String reason;

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
     * -1-拒绝，0-未审批,1-待变动，2-已完成
     */
    private MovementStatus status;
}