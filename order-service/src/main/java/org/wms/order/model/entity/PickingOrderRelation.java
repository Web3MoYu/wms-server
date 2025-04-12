package org.wms.order.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 拣货单-订单关联表
 *
 * @TableName picking_order_relation
 */
@TableName(value = "picking_order_relation")
@Data
public class PickingOrderRelation {
    /**
     * 关联ID
     */
    @TableId
    private String id;

    /**
     * 拣货单ID
     */
    private String pickingId;

    /**
     * 出库订单ID
     */
    private String orderId;

    /**
     * 出库订单编号
     */
    private String orderNo;

    /**
     * 状态：0-待拣货，1-拣货中，2-已完成，3-异常
     */
    private Integer status;

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