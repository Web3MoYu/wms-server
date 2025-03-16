package org.wms.msg.model.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MsgPageDto {
    String senderId;
    String type;
    String title;
    Integer readStatus;
    Integer priority;
    LocalDateTime startTime;
    LocalDateTime endTime;
    Integer page;
    Integer pageSize;
}
