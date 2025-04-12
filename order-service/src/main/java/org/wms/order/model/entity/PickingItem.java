package org.wms.order.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.wms.common.model.Location;
import org.wms.order.model.enums.PickingStatus;

/**
 * 拣货明细表
 *
 * @TableName picking_item
 */
@TableName(value = "picking_item")
@Data
public class PickingItem {
    /**
     * 明细ID
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
     * 出库订单明细ID
     */
    private String orderItemId;

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
     * 批次号
     */
    private String batchNumber;

    /**
     * 区域ID
     */
    private String areaId;

    /**
     * 出库的具体位置，格式
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
     * 预期数量
     */
    private Integer expectedQuantity;

    /**
     * 实际拣货数量
     */
    private Integer actualQuantity;

    /**
     * 状态：0-待拣货，1-拣货中，2-已完成，3-异常
     */
    private PickingStatus status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 拣货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pickingTime;

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