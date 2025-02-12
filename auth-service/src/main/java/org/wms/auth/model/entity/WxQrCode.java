package org.wms.auth.model.entity;

import lombok.Data;

@Data
public class WxQrCode {
    private Integer code;
    private String msg;
    private String type;
    private String url;
    private String qrcode;
}