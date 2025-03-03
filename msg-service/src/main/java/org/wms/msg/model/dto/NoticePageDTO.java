package org.wms.msg.model.dto;


import lombok.Data;

@Data
public class NoticePageDTO {
    private Integer page;
    private Integer pageSize;
    private Integer status;
    private Integer priority;
    private Integer isTop;
    private String publisher;
}
