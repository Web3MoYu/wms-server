package org.wms.order.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.wms.common.model.Location;
import org.wms.order.model.enums.OrderStatusEnums;
import org.wms.order.model.enums.QualityStatusEnums;

/**
 * 出库订单明细表
 *
 * @TableName order_out_item
 */
@TableName(value = "order_out_item")
@Data
public class OrderOutItem {
    /**
     * 明细ID
     */
    @TableId
    private String id;

    /**
     * 出库订单ID
     */
    private String orderId;

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品编码
     */
    private String productCode;

    /**
     * 预期数量
     */
    private Integer expectedQuantity;

    /**
     * 实际数量也是合格数量
     */
    private Integer actualQuantity;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 区域ID
     */
    private String areaId;

    /**
     * 具体位置，格式
     * [
     * {
     * shelfId:,
     * storageIds:[]
     * }
     * ]
     */
    @TableField(typeHandler = org.wms.common.handler.LocationTypeHandler.class)
    private List<Location> location;

    /**
     * 批次号
     */
    private String batchNumber;

    /**
     * 生产日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate productionDate;


    /**
     * 状态：0-待审核，1-审批通过，2-入库中/出库中，3-已完成，-1-已取消 -2-审批拒绝
     */
    private OrderStatusEnums status;

    /**
     * 质检状态：0-未质检，1-质检通过，2-质检不通过，3-部分异常
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