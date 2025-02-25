package org.wms.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.minio.MinioClient;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wms.common.entity.User;
import org.wms.common.exception.BizException;
import org.wms.common.model.Result;
import org.wms.common.utils.UploadUtils;
import org.wms.sys.mapper.UserRoleMapper;
import org.wms.sys.service.UserService;

/**
 * 用户管理相关接口
 */
@RestController
@RequestMapping("/sys")
public class UserController {

    @Resource
    MinioClient minioClient;

    @Resource
    UserService userService;


    @Resource
    UserRoleMapper userRoleMapper;


    /**
     * 分页查询用户信息
     *
     * @param page
     * @param pageSize
     * @param nickName
     * @return
     */
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

    /**
     * 修改个人信息
     *
     * @param user
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/personal/{type}")
    public Result<User> updatePersonal(@PathVariable Integer type, @RequestBody User user) {
        User result = userService.updatePersonalInfo(type, user);

        return Result.success(result, "修改成功");
    }

    /**
     * 将头像上传到临时取悦
     * @param file
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/img/avatar")
    public Result<String> uploadAvatar(@RequestParam("file") MultipartFile file) {
        try {
            String url = UploadUtils.uploadTemp(UploadUtils.AVATAR, file, minioClient);
            return Result.success(url, "上传图片成功");
        } catch (Exception e) {
            throw new BizException(403, "文件上传失败");
        }
    }
}
