package org.wms.auth.service;


import org.wms.auth.model.dto.LoginDto;
import org.wms.auth.model.entity.WxQrCode;
import org.wms.auth.model.vo.LoginVo;
import org.wms.auth.model.vo.WxLoginVo;
import org.wms.common.model.Result;

/**
 * 权限接口
 */
public interface AuthService {
    /**
     * 登录接口，按照账号密码登录后返回登录结果
     *
     * @param username 账号
     * @param password 密码
     * @return LoginVo
     */
    Result<LoginVo> login(String username, String password);

    /**
     * 退出登录接口
     *
     * @return String
     */
    Result<String> logout();


    /**
     * 根据wxId进行登录请求
     *
     * @param wxId
     * @return 是否存在用户的信息
     */
    WxLoginVo login(String wxId);

    /**
     * 根据用户名和密码绑定微信
     *
     * @param param
     * @param wxId
     * @return
     */
    Result<LoginVo> bindWxByLogin(LoginDto param, String wxId);

    /**
     * 获取微信登录二维码
     *
     * @return
     */
    WxQrCode getQrCode();

    /**
     * 获取微信用户的信息，并返回redirect_url
     *
     * @param type
     * @param code
     * @return
     */
    String getWxUserInfo(String type, String code);

    /**
     * 验证token有效性，如果有效返回用户信息
     * @return
     */
    Result<LoginVo> token();

}
