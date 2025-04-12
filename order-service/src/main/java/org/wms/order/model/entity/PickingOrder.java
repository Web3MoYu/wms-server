package org.wms.order.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.wms.order.model.enums.PickingStatus;

/**
 * 拣货单表
 *
 * @TableName picking_order
 */
@TableName(value = "picking_order")
@Data
public class PickingOrder {
    /**
     * 拣货单ID
     */
    @TableId
    private String id;

    /**
     * 拣货单号
     */
    private String pickingNo;

    /**
     * 拣货人员
     */
    private String picker;

    /**
     * 状态：0-待拣货，1-拣货中，2-已完成，3-异常
     */
    private PickingStatus status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 包含订单数量
     */
    private Integer totalOrders;

    /**
     * 包含商品种类数
     */
    private Integer totalItems;

    /**
     * 总拣货数量
     */
    private Integer totalQuantity;
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