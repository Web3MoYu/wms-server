package org.wms.auth.model.entity;

import lombok.Data;

@Data
public class WxQrCode {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String msg;
    /**
     * 类型
     */
    private String type;
    /**
     * 链接
     */
    private String url;
    /**
     * 二维码
     */
    private String qrcode;
}
