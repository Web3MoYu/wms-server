package org.wms.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wms.common.entity.Menu;
import org.wms.sys.Vo.UserRoleVo;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 得到某个用户的所有菜单
     * @param userId id
     * @return List<Menu>
     */
    List<Menu> getMenuByUserId(@Param("userId") String userId);

    /**
     * 按照 orderNum 得到所有菜单信息
     * @return List
     */
    List<Menu> getMenus();

    /**
     * 按照角色id拿到当前角色所拥有的菜单
     * @param roleId 角色id
     * @return List<UserRoleVo>
     */
    List<UserRoleVo> getMenusByRoleId(@Param("roleId") String roleId);
}