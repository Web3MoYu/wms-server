package org.wms.msg.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * websocket推送消息
 *
 * @author 夕四
 * @date 2022-02-23 12:55
 **/
@Data
@NoArgsConstructor
public class WsMsgDataVO<T> {
    /**
     * 推送数据
     */
    private T data;
    /**
     * 标记标签 0：心跳包
     */
    private Integer type;

    public WsMsgDataVO(T data, Integer tag) {
        this.data = data;
        this.type = tag;
    }
}
