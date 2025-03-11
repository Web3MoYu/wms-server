package org.wms.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.wms.api.client.fallback.UserClientFallback;
import org.wms.common.entity.sys.User;

import java.util.List;

/**
 * 用户远程调用接口
 */
@FeignClient(value = "sys-service", contextId = "sys.user", fallbackFactory = UserClientFallback.class)
public interface UserClient {

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return User
     */
    @GetMapping("/api/user/username")
    User getUserByUserName(@RequestParam("username") String username);

    /**
     * 根据用户id获取用户信息
     *
     * @param userId 用户id
     * @return User
     */
    @GetMapping("/api/user/userId")
    User getUserById(@RequestParam("userId") String userId);

    /**
     * 根据邮箱获取用户信息
     *
     * @param email 邮箱
     * @return User
     */
    @GetMapping("/api/user/email")
    User getUserByEmail(@RequestParam("email") String email);

    /**
     * 根据手机号获取用户信息
     *
     * @param phone 手机号
     * @return User
     */

    @GetMapping("/api/user/phone")
    User getUserByPhone(@RequestParam("phone") String phone);

    /**
     * 根据wxId获取用户信息
     *
     * @param wxId wxId
     * @return User
     */
    @GetMapping("/api/user/wxId")
    User getUserByWxId(@RequestParam("wxId") String wxId);

    /**
     * 根据用户id获取权限信息
     *
     * @param userId 用户id
     * @return List
     */
    @GetMapping("/api/user/authorities")
    List<String> getAuthoritiesByUserId(@RequestParam("userId") String userId);

    /**
     * 根据用户id修改该用户的wxId
     *
     * @param wxId
     * @param userId
     * @return
     */
    @GetMapping("/api/user/setWxId")
    Boolean setWxId(@RequestParam("wxId") String wxId, @RequestParam("userId") String userId,
                    @RequestParam("avatar") String avatar);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @PutMapping("/api/user")
    Boolean updatePass(@RequestBody User user);

}
