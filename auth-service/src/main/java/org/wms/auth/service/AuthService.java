package org.wms.auth.service;


import org.wms.auth.model.vo.LoginVo;
import org.wms.common.model.Result;

/**
 * 权限接口
 */
public interface AuthService {
    /**
     * 登录接口，按照账号密码登录后返回登录结果
     * @param username 账号
     * @param password 密码
     * @return LoginVo
     */
    Result<LoginVo> login(String username, String password);

    /**
     * 退出登录接口
     * @return String
     */
    Result<String> logout();
}
