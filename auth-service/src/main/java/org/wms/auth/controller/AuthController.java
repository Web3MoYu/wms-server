package org.wms.auth.controller;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.wms.auth.model.dto.LoginDto;
import org.wms.auth.model.entity.WxQrCode;
import org.wms.auth.model.vo.LoginVo;
import org.wms.auth.service.AuthService;
import org.wms.common.model.Result;

import java.io.IOException;

/**
 * 权限验证Controller
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    AuthService authService;

    @PermitAll
    @PostMapping("/login")
    public Result<LoginVo> login(@RequestBody LoginDto param) {
        return authService.login(param.getUsername(), param.getPassword());
    }

    @GetMapping("/wx/qrcode")
    @PermitAll
    public WxQrCode getQrCode() {
        return authService.getQrCode();
    }

    @GetMapping("/wx/login")
    @PermitAll
    public void getWxUserInfo(String type, String code, HttpServletResponse response) throws IOException {
        String redirectUrl = authService.getWxUserInfo(type, code);
        response.sendRedirect(redirectUrl);
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/logout")
    public Result<String> token() {
        return authService.logout();
    }

    @PermitAll
    @PostMapping("/wx/bind/{wxId}")
    public Result<LoginVo> bindWxByLogin(@RequestBody LoginDto param, @PathVariable String wxId) {
        return authService.bindWxByLogin(param, wxId);
    }
}
