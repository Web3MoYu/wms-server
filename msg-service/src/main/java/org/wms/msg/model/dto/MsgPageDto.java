package org.wms.msg.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class MsgPageDto {
    String senderId;
    String type;
    String title;
    Integer readStatus;
    Integer priority;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime endTime;
    Integer page;
    Integer pageSize;
}
