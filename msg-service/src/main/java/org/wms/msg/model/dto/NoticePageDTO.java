package org.wms.msg.model.dto;


import lombok.Data;

@Data
public class NoticePageDTO {
    /**
     * 页码
     */
    private Integer page;
    /**
     * 每页条数
     */
    private Integer pageSize;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 优先级
     */
    private Integer priority;
    /**
     * 是否置顶
     */
    private Integer isTop;
    /**
     * 发布者
     */
    private String publisher;
    /**
     * 是否显示已经删除的0:不显示，1:显示
     */
    private Integer showDelete;
}
