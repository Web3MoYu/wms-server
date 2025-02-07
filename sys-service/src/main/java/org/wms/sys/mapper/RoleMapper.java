package org.wms.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.wms.common.entity.Role;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据用户id查询出当前用户的角色
     * @param userId id
     * @return Role
     */
    @Select("select sr.role_id, sr.remark, sr.role_name from sys_role sr inner join " +
            "dms.sys_user_role sur on sr.role_id = sur.role_id where sur.user_id = #{userId}")
    Role getRoleByUserID(String userId);

}
