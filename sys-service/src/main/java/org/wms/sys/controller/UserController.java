package org.wms.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.minio.MinioClient;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wms.common.entity.sys.User;
import org.wms.common.exception.BizException;
import org.wms.common.model.Result;
import org.wms.common.utils.UploadUtils;
import org.wms.sys.mapper.UserMapper;
import org.wms.sys.mapper.UserRoleMapper;
import org.wms.sys.model.dto.UserDto;
import org.wms.sys.model.vo.UserVo;
import org.wms.sys.service.UserService;

import java.util.List;
import java.util.Objects;

/**
 * 用户管理相关接口
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {

    @Resource
    MinioClient minioClient;

    @Resource
    UserService userService;

    @Resource
    UserRoleMapper userRoleMapper;

    @Resource
    UserMapper userMapper;


    /**
     * 分页查询用户信息
     *
     * @param page
     * @param pageSize
     * @param nickName
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:user:list')")
    public Result<Page<UserDto>> search(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize,
                                        @RequestParam(required = false) String nickName, @RequestParam(required = false) String realName) {
        Page<UserDto> result = userMapper.pageList(new Page<>(page, pageSize), nickName, realName);
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
     * 检查用户名是否存在
     *
     * @param username 用户名
     * @return 结果
     */
    @GetMapping("/username/{username}")
    @PreAuthorize("isAuthenticated()")
    public Result<Boolean> getUserByUsername(@PathVariable String username) {
        User user = userService.lambdaQuery().eq(User::getUsername, username).one();
        if (Objects.nonNull(user)) {
            throw new BizException("用户名重复");
        }
        return Result.success(false, "不存在");
    }

    /**
     * 检查手机号是否存在
     *
     * @param phone 手机号
     * @return 结果
     */
    @GetMapping("/phone/{phone}")
    @PreAuthorize("isAuthenticated()")
    public Result<Boolean> getUserByPhone(@PathVariable String phone) {
        User user = userService.lambdaQuery().eq(User::getPhone, phone).one();
        if (Objects.nonNull(user)) {
            throw new BizException("手机号重复");
        }
        return Result.success(false, "不存在");
    }

    /**
     * 检查邮箱是否存在
     *
     * @param email 邮箱
     * @return 结果
     */
    @GetMapping("/email/{email}")
    @PreAuthorize("isAuthenticated()")
    public Result<Boolean> getUserByEmail(@PathVariable String email) {
        User user = userService.lambdaQuery().eq(User::getEmail, email).one();
        if (Objects.nonNull(user)) {
            throw new BizException("邮箱重复");
        }
        return Result.success(false, "不存在");
    }


    /**
     * 将头像上传到临时取悦
     *
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

    /**
     * 添加用户
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('sys:user:add')")
    @Transactional
    public Result<String> add(@RequestBody UserVo user) {
        return userService.addUser(user);
    }

    /**
     * 更新用户
     *
     * @param user   用户
     * @param userId 用户id
     * @return 结果
     */
    @PutMapping("/edit/{userId}")
    @PreAuthorize("hasAuthority('sys:user:update')")
    @Transactional
    public Result<String> update(@RequestBody UserVo user, @PathVariable String userId) {
        return userService.updateUser(user, userId);
    }

    /**
     * 删除用户
     *
     * @param userId 用户id
     * @return 结果
     */
    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    @Transactional
    public Result<String> delete(@PathVariable String userId) {
        // 首先删除角色信息
        userRoleMapper.deleteUserRoleById(userId);
        //删除角色
        userService.removeById(userId);
        return Result.success(null, "删除成功");
    }

    /**
     * 获取管理员列表
     *
     * @return 结果
     */
    @GetMapping("/admin/list")
    @PreAuthorize("isAuthenticated()")
    public Result<List<User>> getAdminList() {
        return userService.getAdminList();
    }

    /**
     * 获取所有用户列表
     *
     * @return 结果
     */
    @GetMapping("/listAll")
    @PreAuthorize("isAuthenticated()")
    public Result<List<User>> getAll() {
        return Result.success(userService.list(), "查询成功");
    }
}
