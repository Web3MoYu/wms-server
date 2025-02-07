package org.wms.auth.controller;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wms.api.client.MenuClient;
import org.wms.api.client.UserClient;

/**
 * 权限验证Controller
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    @Resource
    MenuClient menuClient;

    @Resource
    UserClient userClient;

    @GetMapping("/test")
    public String test() {
        log.info("1:{}", menuClient.getMenuTree("1"));
        log.info("2:{}", userClient.getUserById("1"));
        log.info("3:{}", userClient.getUserByUserName("test1"));
        log.info("4:{}", userClient.getUserByWxId("wx"));
        log.info("5:{}", userClient.getUserByEmail("3040114965@qq.com"));
        log.info("6:{}", userClient.getAuthoritiesByUserId("1"));
        log.info("7:{}", userClient.getUserByPhone("11451419198"));
        return "test";
    }


}
