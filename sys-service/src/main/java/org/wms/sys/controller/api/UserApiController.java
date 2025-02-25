package org.wms.sys.controller.api;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.wms.common.entity.User;
import org.wms.sys.service.UserService;

import java.util.List;

/**
 * 暴露用户接口用于RPC远程调用
 */
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);
    @Resource
    UserService userService;

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return User
     */
    @GetMapping("/username")
    public User getUserByUserName(@RequestParam("username") String username) {
        log.info("getUserByUserName调用开始,username:{}", username);
        return userService.lambdaQuery().eq(User::getUsername, username).one();
    }

    /**
     * 根据用户id获取用户信息
     *
     * @param userId 用户id
     * @return User
     */
    @GetMapping("/userId")
    public User getUserById(@RequestParam("userId") String userId) {
        log.info("getUserById调用开始,userId:{}", userId);
        return userService.getById(userId);
    }

    /**
     * 根据邮箱获取用户信息
     *
     * @param email 邮箱
     * @return User
     */
    @GetMapping("/email")
    public User getUserByEmail(@RequestParam("email") String email) {
        log.info("getUserByEmail调用开始,email:{}", email);
        return userService.lambdaQuery().eq(User::getEmail, email).one();
    }

    /**
     * 根据手机号获取用户信息
     *
     * @param phone 手机号
     * @return User
     */

    @GetMapping("/phone")
    public User getUserByPhone(@RequestParam("phone") String phone) {
        log.info("getUserByPhone调用开始,phone:{}", phone);
        return userService.lambdaQuery().eq(User::getPhone, phone).one();
    }

    /**
     * 根据wxId获取用户信息
     *
     * @param wxId wxId
     * @return User
     */
    @GetMapping("/wxId")
    public User getUserByWxId(@RequestParam("wxId") String wxId) {
        log.info("getUserByWxId调用开始,wxId:{}", wxId);
        return userService.lambdaQuery().eq(User::getWxId, wxId).one();
    }

    /**
     * 根据用户id获取权限信息
     *
     * @param userId 用户id
     * @return List
     */
    @GetMapping("/authorities")
    @Cacheable(value = "cache::authorities:", key = "#userId")
    public List<String> getAuthoritiesByUserId(@RequestParam("userId") String userId) {
        log.info("getAuthoritiesByUserId调用开始,userId:{}", userId);
        return userService.getAuthorities(userId);
    }

    /**
     * 设置微信id
     * @param wxId
     * @param userId
     * @param avatar
     * @return
     */
    @GetMapping("/setWxId")
    public Boolean setWxId(@RequestParam("wxId") String wxId, @RequestParam("userId") String userId,
                           @RequestParam("avatar") String avatar) {
        log.info("setWxId调用开始,wxId:{}", wxId);
        return userService.lambdaUpdate()
                .set(User::getWxId, wxId)
                .set(User::getAvatar, avatar)
                .eq(User::getUserId, userId).update();
    }

    @PutMapping
    public Boolean updatePass(@RequestBody User user) {
        return userService.lambdaUpdate()
                .set(User::getSalt, user.getSalt())
                .set(User::getPassword, user.getPassword())
                .eq(User::getUserId, user.getUserId()).update();
    }


}