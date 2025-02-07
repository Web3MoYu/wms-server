package org.wms.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限验证Controller
 */
@RestController
@RequestMapping("/auth")
public class AuthController {


    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
