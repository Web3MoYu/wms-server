package org.wms.order.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.wms.order.model.enums.InspectStatus;
import org.wms.order.model.enums.InspectType;

/**
 * 质检记录表
 *
 * @TableName quality_inspection
 */
@TableName(value = "quality_inspection")
@Data
public class Inspection {
    /**
     * 质检ID
     */
    @TableId
    private String id;

    /**
     * 质检编号
     */
    private String inspectionNo;

    /**
     * 质检类型：1-入库质检，2-出库质检，3-库存质检
     */
    private InspectType inspectionType;

    /**
     * 关联订单ID
     */
    private String relatedOrderId;

    /**
     * 关联订单编号
     */
    private String relatedOrderNo;

    /**
     * 质检员
     */
    private String inspector;

    /**
     * 质检时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime inspectionTime;

    /**
     * 质检状态：1-通过，2-不通过，3-部分异常
     */
    private InspectStatus status;

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