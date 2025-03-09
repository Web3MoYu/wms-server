package org.wms.auth.controller;

import java.io.IOException;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wms.auth.model.dto.ChangePassDto;
import org.wms.auth.model.dto.LoginDto;
import org.wms.auth.model.entity.WxQrCode;
import org.wms.auth.model.vo.LoginVo;
import org.wms.auth.service.AuthService;
import org.wms.common.model.Result;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 权限验证Controller
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    AuthService authService;

    /**
     * 登录
     *
     * @param param 登录参数
     * @return 登录结果
     */
    @PermitAll
    @PostMapping("/login")
    public Result<LoginVo> login(@RequestBody LoginDto param) {
        return authService.login(param.getUsername(), param.getPassword());
    }

    /**
     * 获取微信二维码
     *
     * @return 微信二维码
     */
    @GetMapping("/wx/qrcode")
    @PermitAll
    public WxQrCode getQrCode() {
        return authService.getQrCode();
    }

    /**
     * 获取微信用户信息
     *
     * @param type     类型
     * @param code     代码
     * @param response 响应
     */
    @GetMapping("/wx/login")
    @PermitAll
    public void getWxUserInfo(String type, String code, HttpServletResponse response) throws IOException {
        String redirectUrl = authService.getWxUserInfo(type, code);
        response.sendRedirect(redirectUrl);
    }

    /**
     * 退出登录
     *
     * @return 退出结果
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/logout")
    public Result<String> logout() {
        return authService.logout();
    }

    /**
     * 绑定微信登录
     *
     * @param param 登录参数
     * @param wxId  微信ID
     * @return 登录结果
     */
    @PermitAll
    @PostMapping("/wx/bind/{wxId}")
    public Result<LoginVo> bindWxByLogin(@RequestBody LoginDto param, @PathVariable String wxId) {
        return authService.bindWxByLogin(param, wxId);
    }

    /**
     * 获取token
     *
     * @return 登录结果
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/token")
    public Result<LoginVo> token() {
        return authService.token();
    }

    /**
     * 修改密码
     *
     * @param param 修改密码参数
     * @return 修改密码结果
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/modifyPass")
    public Result<String> modifyPass(@RequestBody ChangePassDto param) {
        return authService.modifyPass(param);
    }

}
