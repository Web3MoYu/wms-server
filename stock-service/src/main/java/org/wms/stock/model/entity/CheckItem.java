package org.wms.stock.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.wms.stock.model.enums.CheckDiffStatus;
import org.wms.stock.model.enums.CheckStatus;

/**
 * 盘点明细表
 *
 * @TableName stock_check_item
 */
@TableName(value = "stock_check_item")
@Data
public class CheckItem {
    /**
     * 明细ID
     */
    @TableId
    private String id;

    /**
     * 盘点单ID
     */
    private String checkId;

    /**
     * 产品ID
     */
    private String stockId;

    /**
     * 系统数量
     */
    private Integer systemQuantity;

    /**
     * 实际数量
     */
    private Integer actualQuantity;

    /**
     * 差异数量
     */
    private Integer differenceQuantity;

    /**
     * 状态：0-待盘点，1-已盘点
     */
    private CheckStatus status;

    /**
     * 是否有差异：0-无，1-有
     */
    private CheckDiffStatus isDifference;

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