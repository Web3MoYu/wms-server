package org.wms.stock.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AlertQueryDto {

    /**
     * 当前页码
     */
    Integer page;
    /**
     * 每页条数
     */
    Integer pageSize;

    /**
     * 1-低于最小库存，2-超过最大库存
     */
    Integer alertType;

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
     * 是否处理：0-否，1-是
     */
    Integer isHandled;

    /**
     * 处理人
     */
    String handler;
}
