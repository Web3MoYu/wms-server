package org.wms.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.wms.common.entity.User;
import org.wms.sys.mapper.UserMapper;
import org.wms.sys.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public List<String> getAuthorities(String userId) {
        return userMapper.getAuthorities(userId);
    }
}
