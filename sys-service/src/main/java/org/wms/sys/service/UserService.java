package org.wms.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.wms.common.entity.sys.User;
import org.wms.common.model.Result;
import org.wms.sys.model.vo.UserVo;

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

    /**
     * 添加用户信息
     *
     * @param user
     * @return
     */
    Result<String> addUser(UserVo user);

    /**
     * 修改用户信息
     *
     * @param user
     * @param userId
     * @return
     */
    Result<String> updateUser(UserVo user, String userId);

    /**
     * 获取管理员列表
     *
     * @return 管理员列表
     */
    Result<List<User>> getAdminList();

}
