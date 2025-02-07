package org.wms.sys.controller.api;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.wms.common.entity.User;
import org.wms.sys.service.UserService;

import java.util.List;

/**
 * 暴露用户接口用于RPC远程调用
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class UserApiController {

    @Resource
    UserService userService;

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return User
     */
    @GetMapping("/user")
    public User getUserByUserName(String username) {
        return userService.lambdaQuery().eq(User::getUsername, username).one();
    }

    /**
     * 根据用户id获取用户信息
     *
     * @param userId 用户id
     * @return User
     */
    @GetMapping("/user")
    public User getUserById(String userId) {
        return userService.getById(userId);
    }

    /**
     * 根据邮箱获取用户信息
     *
     * @param email 邮箱
     * @return User
     */
    @GetMapping("/user")
    public User getUserByEmail(String email) {
        return userService.lambdaQuery().eq(User::getEmail, email).one();
    }

    /**
     * 根据用户id获取权限信息
     *
     * @param userId 用户id
     * @return List
     */
    @GetMapping("/authorities")
    @Cacheable(value = "cache::authorities:", key = "#userId")
    public List<String> getAuthoritiesByUserId(String userId) {
        return userService.getAuthorities(userId);
    }

}
