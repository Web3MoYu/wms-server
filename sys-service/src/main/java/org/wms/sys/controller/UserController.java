package org.wms.sys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理相关接口
 */
@RestController
@RequestMapping("/sys")
public class UserController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
