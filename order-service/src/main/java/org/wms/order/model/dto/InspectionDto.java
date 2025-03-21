package org.wms.order.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InspectionDto {

    // 基本分页参数
    private Integer page = 1;
    private Integer pageSize = 10;

    // 质检编号模糊搜索
    private String inspectionNo;
    /**
     * 质检类型：1-入库质检，2-出库质检，3-库存质检,为null查询全部
     */
    private Integer inspectionType;

    // 订单编号，模糊搜索
    private String relatedOrderNo;

    // 质检员
    private String inspector;

    // 时间范围 对应 Inspection.inspectionTime
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;    // 开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;      // 结束时间

    /**
     * 质检状态：1-通过，2-不通过，3-部分异常,为null查询全部
     */
    private Integer status;

    // 创建时间排序 true-升序 false-降序
    private Boolean createTimeAsc;
}
