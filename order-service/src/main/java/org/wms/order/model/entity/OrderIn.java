package org.wms.order.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.wms.order.model.enums.OrderInType;
import org.wms.order.model.enums.OrderStatusEnums;
import org.wms.common.enums.order.OrderType;
import org.wms.order.model.enums.QualityStatusEnums;

/**
 * 入库订单表
 *
 * @TableName order_in
 */
@TableName(value = "order_in")
@Data
public class OrderIn {
    /**
     * 入库订单ID
     */
    @TableId
    private String id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 类型：0-出库，1入库
     */
    private OrderType type;

    /**
     * 订单类型：1-采购入库，2-自动入库
     */
    private OrderInType orderType;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 审核人
     */
    private String approver;

    /**
     * 质检员
     */
    private String inspector;

    /**
     * 预计到达时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expectedTime;

    /**
     * 实际到达时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime actualTime;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 总数量
     */
    private Integer totalQuantity;

    /**
     * 状态：0-待审核，1-审批通过，2-入库中/出库中，3-已完成，-1-已取消 -2-审批拒绝
     */
    private OrderStatusEnums status;

    /**
     * 质检状态：质检状态：0-未质检，1-质检通过，2-质检不通过,3-部分异常
     */
    private QualityStatusEnums qualityStatus;

    /**
     * 备注
     */
    private String remark;

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