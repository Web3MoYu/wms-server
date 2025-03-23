package org.wms.order.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Integer status; //状态：0-待审核，1-已审核，2-入库中/出库中，3-已完成，-1-已取消 -2-审批拒绝

    // 时间范围
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;    // 开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;      // 结束时间

    // 排序参数
    private Boolean createTimeAsc;      // 创建时间排序 true-升序 false-降序
}