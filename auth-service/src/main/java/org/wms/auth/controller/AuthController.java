package org.wms.auth.controller;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.wms.auth.model.dto.LoginDto;
import org.wms.auth.model.vo.LoginVo;
import org.wms.auth.service.AuthService;
import org.wms.common.model.Result;

/**
 * 权限验证Controller
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);


    @Resource
    AuthService authService;

    @PermitAll
    @PostMapping("/login")
    public Result<LoginVo> login(@RequestBody LoginDto param) {
        return authService.login(param.getUsername(), param.getPassword());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/logout")
    public Result<String> token() {
        return authService.logout();
    }


}
