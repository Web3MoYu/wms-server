package org.wms.auth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.wms.api.client.MenuClient;
import org.wms.api.client.UserClient;
import org.wms.auth.model.dto.LoginDto;
import org.wms.auth.model.entity.WxQrCode;
import org.wms.auth.model.vo.LoginVo;
import org.wms.auth.model.vo.WxLoginVo;
import org.wms.auth.model.vo.WxUserInfo;
import org.wms.auth.properties.ThirdLogin;
import org.wms.auth.service.AuthService;
import org.wms.common.entity.MenuTree;
import org.wms.common.entity.User;
import org.wms.common.exception.BizException;
import org.wms.common.model.Result;
import org.wms.common.utils.DigestsUtils;
import org.wms.common.utils.JWTUtils;
import org.wms.common.utils.RedisUtils;
import org.wms.security.util.SecurityUtil;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Resource
    UserClient userClient;

    @Resource
    ThirdLogin thirdLogin;

    @Resource
    MenuClient menuClient;

    @Resource
    RestTemplate restTemplate;

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
        LoginVo loginVo = setUserInfo(user);
        return Result.success(loginVo, "登录成功");
    }

    @Override
    public Result<String> logout() {
        String userID = SecurityUtil.getUserID();
        redisTemplate.delete(RedisUtils.TOKEN_KEY + userID);
        redisTemplate.delete(RedisUtils.PERMISSIONS_KEY + userID);
        return Result.success("退出登录成功");
    }

    @Override
    public WxLoginVo login(String wxId) {
        User user = userClient.getUserByWxId(wxId);
        if (ObjectUtil.hasEmpty(user)) {
            return new WxLoginVo(false, null);
        }
        // 拿到权限信息和token存到redis中
        LoginVo loginVo = setUserInfo(user);
        return new WxLoginVo(true, loginVo);
    }

    @Override
    public Result<LoginVo> bindWxByLogin(LoginDto param, String wxId) {
        String username = param.getUsername();
        String password = param.getPassword();
        // 判断是否是邮箱登录
        User user = null;
        if (username.contains("@")) {
            log.info("邮箱绑定");
            user = userClient.getUserByEmail(username);
        } else {
            log.info("手机号绑定");
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
        LoginVo loginVo = setUserInfo(user);

        // 从Redis中获取用户信息
        Object o = redisTemplate.opsForValue().get(RedisUtils.WX_USER_INFO_KEY + wxId);
        WxUserInfo bean = JSONUtil.parse(o).toBean(WxUserInfo.class);
        // 更改用户的微信ID和头像
        Boolean b = userClient.setWxId(wxId, user.getUserId(), bean.getFaceimg());
        if (!b) {
            throw new BizException("绑定失败");
        }
        loginVo.getUser().setAvatar(bean.getFaceimg());
        return Result.success(loginVo, "登录成功");
    }

    @Override
    public WxQrCode getQrCode() {
        String url = "https://uniqueker.top/connect.php";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("act", "login");
        params.add("appid", thirdLogin.getAppId());
        params.add("appkey", thirdLogin.getAppKey());
        params.add("type", "wx");
        params.add("redirect_uri", thirdLogin.getRedirectUrl());
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        URI uri = builder.queryParams(params).build().encode().toUri();
        ResponseEntity<WxQrCode> exchange = restTemplate.getForEntity(uri, WxQrCode.class);
        return exchange.getBody();
    }

    @Override
    public String getWxUserInfo(String type, String code) {
        String url = "https://uniqueker.top/connect.php";
        // 设置请求参数
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("act", "callback");
        params.add("appid", thirdLogin.getAppId());
        params.add("appkey", thirdLogin.getAppKey());
        params.add("type", type);
        params.add("code", code);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        URI uri = builder.queryParams(params).build().encode().toUri();
        ResponseEntity<WxUserInfo> exchange = restTemplate.getForEntity(uri, WxUserInfo.class);
        WxUserInfo userInfo = exchange.getBody();

        WxLoginVo login = null;
        if (userInfo != null && userInfo.getCode() == 0) {
            login = this.login(userInfo.getSocial_uid());
        }
        assert login != null;
        // 讲微信用户信息保存到redis中
        redisTemplate.opsForValue().set(RedisUtils.WX_USER_INFO_KEY + userInfo.getSocial_uid(),
                JSONUtil.toJsonStr(userInfo), 5, TimeUnit.MINUTES);
        // 将用户信息传递到前端
        String directUrl = "http://dev.myapp.com:9090/"
                + "?binding=" + login.getBinding() + "&wxId=" + userInfo.getSocial_uid();
        if (login.getBinding()) {
            directUrl += "&userInfo="
                    + URLEncoder.encode(JSONUtil.toJsonStr(login.getUserInfo()), StandardCharsets.UTF_8);
        }
        return directUrl;

    }


    private LoginVo setUserInfo(User user) {
        String token = JWTUtils.creatToken(user.getUserId());
        // 获取权限信息
        List<MenuTree> menuTree = menuClient.getMenuTree(user.getUserId());
        List<String> authorities = userClient.getAuthoritiesByUserId(user.getUserId());
        // 保存到redis中
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
        return loginVo;
    }
}
