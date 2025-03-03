package org.wms.msg.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import lombok.Data;
import org.wms.msg.model.enums.NoticeIsTop;
import org.wms.msg.model.enums.NoticePriority;
import org.wms.msg.model.enums.NoticeStatus;

/**
 * 公告表
 *
 * @TableName notif_notice
 */
@TableName(value = "notif_notice")
@Data
public class Notice {
    /**
     * 公告ID
     */
    @TableId
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布人
     */
    private String publisher;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 状态：0-未发布，1-已发布，2-废弃
     */
    private NoticeStatus status;

    /**
     * 优先级：0-普通，1-重要，2-紧急
     */
    private NoticePriority priority;

    /**
     * 是否置顶：0-否，1-是
     */
    private NoticeIsTop isTop;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}