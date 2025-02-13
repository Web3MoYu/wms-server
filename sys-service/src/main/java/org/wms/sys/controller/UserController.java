package org.wms.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wms.common.entity.User;
import org.wms.common.model.Result;
import org.wms.sys.mapper.UserRoleMapper;
import org.wms.sys.service.UserService;

/**
 * 用户管理相关接口
 */
@RestController
@RequestMapping("/sys")
public class UserController {

    @Resource
    UserService userService;


    @Resource
    UserRoleMapper userRoleMapper;


    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:user:index')")
    public Result<Page<User>> search(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize, @RequestParam(required = false) String nickName) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(User::getUserId, User::getNickName, User::getPhone,
                        User::getUsername, User::getSex, User::getAvatar, User::getEmail)
                .like(nickName != null, User::getNickName, nickName);
        Page<User> result = userService.page(new Page<>(page, pageSize), wrapper);
        return Result.success(result, "查询成功");
    }
}
