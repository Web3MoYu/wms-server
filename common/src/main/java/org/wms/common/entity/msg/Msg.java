package org.wms.common.entity.msg;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.wms.common.enums.msg.*;

/**
 * 消息通知表
 *
 * @TableName notif_msg
 */
@TableName(value = "notif_msg")
@Data
public class Msg {
    /**
     * 通知ID
     */
    @TableId
    private String id;

    /**
     * 通知类型：1-库存预警，2-质检通知，3-订单状态，4-库位变更，5-库存盘点，6-其他
     */
    private MsgTypeEnums type;

    /**
     * 通知标题
     */
    private String title;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 接收人ID
     */
    private String recipientId;

    /**
     * 接收人姓名
     */
    private String recipientName;

    /**
     * 发送人ID
     */
    private String senderId;

    /**
     * 发送人姓名
     */
    private String senderName;

    /**
     * 阅读状态：0-未读，1-已读
     */
    private MsgReadEnums readStatus;

    /**
     * 优先级：0-普通，1-重要，2-紧急
     */
    private MsgPriorityEnums priority;

    /**
     * 关联业务ID,可能是入库id，出库id等用于跳转页面
     */
    private String relatedBizId;

    /**
     * 关联业务类型：1-入库单，2-出库单，3-质检单，4-库位变更，5-库存预警，6-库存盘点
     */
    private MsgBizEnums relatedBizType;

    /**
     * 发送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

    /**
     * 阅读时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime readTime;

    /**
     * 是否系统通知：0-否，1-是
     */
    private MsgIsSystemEnums isSystem;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    public Msg(MsgTypeEnums type, String title, String content,
               String recipientId, String recipientName, String senderId,
               String senderName, MsgPriorityEnums priority, String bizId, MsgBizEnums bizType) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.recipientId = recipientId;
        this.recipientName = recipientName;
        this.senderId = senderId;
        this.senderName = senderName;
        this.priority = priority;
        this.relatedBizId = bizId;
        this.relatedBizType = bizType;
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();

    }

    public Msg() {

    }
}