package org.wms.auth.model.dto;

import lombok.Data;

/**
 * 登录dto
 */
@Data
public class LoginDto {

    /**
     * 用户名
     */
    String username;
    /**
     * 密码
     */
    String password;
}
