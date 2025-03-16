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
import org.wms.order.model.enums.OrderItemStatus;
import org.wms.order.model.enums.QualityStatusEnums;

/**
 * 入库订单明细表
 *
 * @TableName order_in_item
 */
@TableName(value = "order_in_item")
@Data
public class OrderInItem {
    /**
     * 明细ID
     */
    @TableId
    private String id;

    /**
     * 入库订单ID
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
     * 实际数量
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
     * 过期日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiryDate;

    /**
     * 状态：0-待开始，1-部分完成，2-已完成
     */
    private OrderItemStatus status;

    /**
     * 质检状态：0-未质检，1-质检通过，2-质检不通过
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