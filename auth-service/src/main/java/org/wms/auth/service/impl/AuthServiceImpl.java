package org.wms.auth.service.impl;

import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.wms.api.client.MenuClient;
import org.wms.api.client.UserClient;
import org.wms.auth.model.vo.LoginVo;
import org.wms.auth.service.AuthService;
import org.wms.common.entity.MenuTree;
import org.wms.common.entity.User;
import org.wms.common.exception.BizException;
import org.wms.common.model.Result;
import org.wms.common.utils.DigestsUtils;
import org.wms.common.utils.JWTUtils;
import org.wms.common.utils.RedisUtils;
import org.wms.security.util.SecurityUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Resource
    UserClient userClient;

    @Resource
    MenuClient menuClient;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public Result<LoginVo> login(String username, String password) {
        log.info("登录开始，username:{}, password:{}", username, password);
        // 判断是否是邮箱登录
        User user = null;
        if (username.contains("@")) {
            log.info("邮箱登录");
            user = userClient.getUserByEmail(username);
        } else {
            log.info("手机号登录");
            user = userClient.getUserByPhone(username);
        }
        // 用户名不存在抛出异常信息
        if (user == null) {
            throw new BizException("用户名或密码错误");
        }
        // 匹配密码是否正确
        if (!DigestsUtils.matches(password, user.getSalt(), user.getPassword())) {
            throw new BizException("用户名或密码错误");
        }

        // 拿到权限信息和token存到redis中
        String token = JWTUtils.creatToken(user.getUserId());
        List<MenuTree> menuTree = menuClient.getMenuTree(user.getUserId());
        List<String> authorities = userClient.getAuthoritiesByUserId(user.getUserId());
        redisTemplate.opsForValue().set(RedisUtils.TOKEN_KEY + user.getUserId(),
                token, JWTUtils.EXPIRE_TIME, TimeUnit.DAYS);
        redisTemplate.opsForValue().set(RedisUtils.PERMISSIONS_KEY + user.getUserId(),
                JSONUtil.toJsonStr(authorities), JWTUtils.EXPIRE_TIME, TimeUnit.DAYS);

        LoginVo loginVo = new LoginVo();
        loginVo.setMenuTree(menuTree);
        loginVo.setToken(token);
        user.setPassword(null);
        user.setSalt(null);
        loginVo.setUser(user);
        return Result.success(loginVo, "登录成功");
    }

    @Override
    public Result<String> logout() {
        String userID = SecurityUtil.getUserID();
        redisTemplate.delete(RedisUtils.TOKEN_KEY + userID);
        redisTemplate.delete(RedisUtils.PERMISSIONS_KEY + userID);
        return Result.success("退出登录成功");
    }
}
