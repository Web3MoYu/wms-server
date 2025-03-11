package org.wms.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.minio.MinioClient;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.wms.common.entity.sys.User;
import org.wms.common.exception.BizException;
import org.wms.common.model.Result;
import org.wms.common.utils.DigestsUtils;
import org.wms.common.utils.UploadUtils;
import org.wms.security.util.SecurityUtil;
import org.wms.sys.entity.UserRole;
import org.wms.sys.mapper.UserMapper;
import org.wms.sys.mapper.UserRoleMapper;
import org.wms.sys.model.vo.UserVo;
import org.wms.sys.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    UserRoleMapper userRoleMapper;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Resource
    UserMapper userMapper;

    @Resource
    MinioClient minioClient;

    @Override
    public List<String> getAuthorities(String userId) {
        return userMapper.getAuthorities(userId);
    }

    @Override
    public User updatePersonalInfo(Integer type, User user) {
        String userId = SecurityUtil.getUserID();
        if (type == 1) {
            try {
                String avatar = this.lambdaQuery().eq(User::getUserId, userId).one().getAvatar();
                String url = UploadUtils.moveFile(minioClient, "temp", "wms", user.getAvatar());
                if (Objects.nonNull(avatar) && avatar.startsWith("http")) {
                    UploadUtils.delete(UploadUtils.AVATAR, avatar, minioClient);
                }
                user.setAvatar(url);
            } catch (Exception e) {
                throw new BizException("修改个人信息失败");
            }
        }
        boolean update = this.lambdaUpdate()
                .set(User::getNickName, user.getNickName())
                .set(User::getRealName, user.getRealName())
                .set(User::getAvatar, user.getAvatar())
                .eq(User::getUserId, userId).update();
        if (!update) {
            throw new BizException("修改个人信息失败");
        }
        return user;
    }

    @Override
    public Result<String> addUser(UserVo user) {
        User one = this.lambdaQuery().eq(User::getUsername, user.getUsername()).one();
        if (Objects.nonNull(one)) {
            throw new BizException("用户名重复");
        }
        one = this.lambdaQuery().eq(User::getEmail, user.getEmail()).one();
        if (Objects.nonNull(one)) {
            throw new BizException("邮箱重复");
        }
        one = this.lambdaQuery().eq(User::getPhone, user.getPhone()).one();
        if (Objects.nonNull(one)) {
            throw new BizException("手机号重复");
        }
        // 修改时间
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        // 创建密码
        Map<String, String> map = DigestsUtils.getPassword();
        String salt = map.get(DigestsUtils.SALT);
        String pwd = map.get(DigestsUtils.PASSWORD);
        user.setSalt(salt);
        user.setPassword(pwd);
        this.save(user);

        // 添加角色信息
        UserRole userRole = new UserRole();
        userRole.setUserRoleId(String.valueOf(IdWorker.getId()));
        userRole.setRoleId(user.getRoleId());
        userRole.setUserId(user.getUserId());
        userRoleMapper.insert(userRole);
        return Result.success(null, "添加成功");

    }

    @Override
    public Result<String> updateUser(UserVo user, String userId) {
        user.setUserId(userId);
        user.setUpdateTime(new Date());
        // 是否重置密码
        if (user.isResetPassword()) {
            Map<String, String> map = DigestsUtils.getPassword();
            String salt = map.get(DigestsUtils.SALT);
            String pwd = map.get(DigestsUtils.PASSWORD);
            user.setSalt(salt);
            user.setPassword(pwd);
        }
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        // 修改角色信息
        if (user.getRoleId() != null) {
            userRoleMapper.updateRoleByUserId(userId, user.getRoleId());
        }
        // 修改其他信息
        wrapper.set(user.getUsername() != null, User::getUsername, user.getUsername())
                .set(user.getPhone() != null, User::getPhone, user.getPhone())
                .set(user.getRealName() != null, User::getRealName, user.getRealName())
                .set(user.getNickName() != null, User::getNickName, user.getNickName())
                .set(user.getSex() != null, User::getSex, user.getSex())
                .set(user.getUpdateTime() != null, User::getUpdateTime, user.getUpdateTime())
                .set(user.getSalt() != null, User::getSalt, user.getSalt())
                .set(user.getEmail() != null, User::getEmail, user.getEmail())
                .set(user.getPassword() != null, User::getPassword, user.getPassword())
                .eq(User::getUserId, user.getUserId());
        this.update(wrapper);
        return Result.success(null, "修改成功");
    }

    @Override
    public Result<List<User>> getAdminList() {
        List<User> list = userMapper.getAminList();
        return Result.success(list, "查询成功");
    }

}
