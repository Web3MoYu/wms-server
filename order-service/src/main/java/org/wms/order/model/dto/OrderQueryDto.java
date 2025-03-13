package org.wms.order.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderQueryDto {
    // 基本分页参数
    private Integer page = 1;
    private Integer pageSize = 10;

    // 订单类型：0-入库订单，1-出库订单，不传则查询全部
    private Integer orderType;

    // 查询条件
    private String orderNo;             // 订单编号(模糊查询)
    private Integer inspectionStatus;   // 质检状态
    private String creatorId;           // 创建人ID
    private String approverId;          // 审核人id
    private String inspectorId;         // 质检人员ID

    // 时间范围
    private LocalDateTime startTime;    // 开始时间
    private LocalDateTime endTime;      // 结束时间

    // 排序参数
    private Boolean createTimeAsc;      // 创建时间排序 true-升序 false-降序
}