package org.wms.common.entity.order;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.wms.common.enums.order.InspectItemStatus;
import org.wms.common.enums.order.ReceiveStatus;
import org.wms.common.model.Location;

/**
 * 质检明细表
 *
 * @TableName quality_inspection_item
 */
@TableName(value = "quality_inspection_item")
@Data
public class InspectionItem {
    /**
     * 明细ID
     */
    @TableId
    private String id;

    /**
     * 质检记录ID
     */
    private String inspectionId;

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 批次号
     */
    private String batchNumber;

    /**
     * 区域id
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
    private List<Location> location;

    /**
     * 质检数量
     */
    private Integer inspectionQuantity;

    /**
     * 合格数量
     */
    private Integer qualifiedQuantity;

    /**
     * 不合格数量
     */
    private Integer unqualifiedQuantity;

    /**
     * 质检结果：1-合格，2-不合格
     */
    private InspectItemStatus qualityStatus;

    /**
     * 上架状态：0-未开始，1-已完成,2-进行中
     */
    private ReceiveStatus receiveStatus;

    /**
     * 异常原因
     */
    private String reason;

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