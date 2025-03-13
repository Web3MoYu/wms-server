package org.wms.order.model.vo;

import lombok.Data;
import org.wms.common.entity.sys.User;
import org.wms.order.model.enums.OrderStatusEnums;
import org.wms.order.model.enums.OrderType;
import org.wms.order.model.enums.QualityStatusEnums;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OrderVo {
    /**
     * id
     */
    private String id;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 订单类型：1-入库订单，0-出库订单
     */
    private OrderType type;
    /**
     * 创建人
     */
    private User creator;
    /**
     * 审批人
     */
    private User approver;
    /**
     * 质检员
     */
    private User inspector;
    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 总数量
     */
    private Integer totalQuantity;

    /**
     * 状态：0-待审核，1-已审核，2-部分完成，3-已完成，-1-已取消
     */
    private OrderStatusEnums status;

    /**
     * 质检状态：质检状态：0-未质检，1-质检通过，2-质检不通过
     */
    private QualityStatusEnums qualityStatus;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDate createTime;

}