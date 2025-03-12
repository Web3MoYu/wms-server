package org.wms.common.entity.msg;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WsMsgDataVO<T> {
    /**
     * 推送数据
     */
    private T data;
    /**
     * 消息类型
     * 0：心跳包
     * 1: 消息提醒
     */
    private Integer type;

    private String id;

    public WsMsgDataVO(T data, Integer type, String id) {
        this.data = data;
        this.type = type;
        this.id = id;
    }
}
