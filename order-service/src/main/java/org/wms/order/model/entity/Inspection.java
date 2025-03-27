package org.wms.order.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.wms.common.enums.inspect.InspectType;
import org.wms.common.enums.order.ReceiveStatus;
import org.wms.order.model.enums.QualityStatusEnums;

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
     * 质检状态：0-未质检，1-通过，2-不通过，3-部分异常
     */
    private QualityStatusEnums status;

    /**
     * 上架状态：0-未开始，1-已完成,2-进行中
     */
    private ReceiveStatus receiveStatus;

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