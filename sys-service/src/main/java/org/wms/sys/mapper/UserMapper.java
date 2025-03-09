package org.wms.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wms.common.entity.User;
import org.wms.sys.model.dto.UserDto;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据userId获取当前用户的权限信息
     *
     * @param userId id
     * @return List
     */
    List<String> getAuthorities(String userId);

    /**
     * 分页查询用户列表
     *
     * @param objectPage 分页对象
     * @param nickName   昵称
     * @param realName   真实姓名
     * @return 用户列表
     */
    Page<UserDto> pageList(@Param("page") Page<UserDto> objectPage,
                           @Param("nickName") String nickName, @Param("realName") String realName);

    /**
     * 获取管理员列表
     *
     * @return 管理员列表
     */
    List<User> getAminList();

}




