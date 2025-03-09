package org.wms.auth.model.vo;

import lombok.Data;

@Data
public class WxUserInfo {

    /**
     * 状态码
     */
    Integer code;
    /**
     * 消息
     */
    String msg;
    /**
     * 类型
     */
    String type;
    /**
     * 访问令牌
     */
    String access_token;
    /**
     * 社交ID
     */
    String social_uid;
    /**
     * 头像
     */
    String faceimg;
    /**
     * 昵称
     */
    String nickname;
    /**
     * 性别
     */
    String gender;
    /**
     * 位置
     */
    String location;
    /**
     * IP
     */
    String ip;
    /**
     * 邮箱
     */
    String email;
    /**
     * 手机号
     */
    String mobile;
}
