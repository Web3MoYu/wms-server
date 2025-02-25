package org.wms.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.wms.common.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    /**
     * 根据userId获取用户权限信息
     *
     * @param userId id
     * @return List
     */
    List<String> getAuthorities(String userId);

    /**
     * 更新个人信息包括头像
     *
     * @param type
     * @param user
     * @return
     */
    User updatePersonalInfo(Integer type, User user);
}
