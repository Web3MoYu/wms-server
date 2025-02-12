package org.wms.auth.model.vo;

import lombok.Data;

@Data
public class WxUserInfo {
    Integer code;
    String msg;
    String type;
    String access_token;
    String social_uid;
    String faceimg;
    String nickname;
    String gender;
    String location;
    String ip;
    String email;
    String mobile;
}
