package org.wms.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.minio.MinioClient;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.wms.common.entity.User;
import org.wms.common.exception.BizException;
import org.wms.common.utils.UploadUtils;
import org.wms.security.util.SecurityUtil;
import org.wms.sys.mapper.UserMapper;
import org.wms.sys.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

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
                String avatar = this.lambdaQuery().select(User::getAvatar).eq(User::getUserId, userId).one().getAvatar();
                String url = UploadUtils.moveFile(minioClient, "temp", "wms", user.getAvatar());
                UploadUtils.delete(UploadUtils.AVATAR, avatar, minioClient);
                user.setAvatar(url);
            } catch (Exception e) {
                throw new BizException("修改个人信息失败");
            }
        }
        boolean update = this.lambdaUpdate()
                .set(User::getNickName, user.getNickName())
                .set(User::getAvatar, user.getAvatar())
                .eq(User::getUserId, userId).update();
        if (!update) {
            throw new BizException("修改个人信息失败");
        }
        return user;
    }

}
