package org.wms.stock.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovementDto {

    /**
     * 当前页码
     */
    Integer page;
    /**
     * 每页条数
     */
    Integer pageSize;

    /**
     * 变更编号
     */
    String movementNo;

    /**
     * 变更前区域
     */
    String beforeAreaId;

    /**
     * 变更后区域
     */
    String afterAreaId;

    /**
     * 操作人
     */
    String operator;

    /**
     * 审批人
     */
    String approver;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime startDate;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime endDate;

    /**
     * -1-拒绝，0-未审批,1-待变动，2-已完成
     */
    Integer status;
}
