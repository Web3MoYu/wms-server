package org.wms.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.wms.sys.entity.UserRole;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 更新userId用户的角色为roleId
     *
     * @param userId id
     * @param roleId id
     * @return int
     */
    @Update("update sys_user_role set role_id = #{roleId} where user_id = #{userId}")
    int updateRoleByUserId(String userId, String roleId);

    /**
     * 根据用户id删除当前用户的角色信息
     * @param userId id
     */
    @Update("delete from sys_user_role where user_id = #{userId}")
    void deleteUserRoleById(String userId);
}
