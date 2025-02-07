package org.wms.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.wms.common.entity.Menu;
import org.wms.common.entity.MenuTree;

import java.util.List;

public interface MenuService extends IService<Menu> {
    /**
     * 根据用户id获取菜单树
     * @param userId id
     * @return List<MenuTree>
     */
    List<MenuTree> getTreeMenu(String userId);

    /**
     * 获取所有的菜单列表
     * @return List
     */
    List<MenuTree> allTreeMenu();

    /**
     * 拿到当前角色的菜单列表
     * @param roleId id
     * @return List
     */
    List<String> getTreeMenuByRoleId(String roleId);

}
