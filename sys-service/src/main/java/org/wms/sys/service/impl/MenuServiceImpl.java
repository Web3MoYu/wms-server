package org.wms.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.wms.common.entity.Menu;
import org.wms.common.entity.MenuTree;
import org.wms.sys.Vo.UserRoleVo;
import org.wms.sys.mapper.MenuMapper;
import org.wms.sys.service.MenuService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
        implements MenuService {

    @Resource
    MenuMapper menuMapper;

    @Override
    public List<MenuTree> getTreeMenu(String userId) {
        List<Menu> menus = null;
        if (userId != null) {
            menus = menuMapper.getMenuByUserId(userId);
        } else {
            menus = menuMapper.getMenus();
        }
        Map<String, MenuTree> map = new HashMap<>();
        List<MenuTree> list = new ArrayList<>();
        // 将数据全部放在map集合中
        for (Menu menu : menus) {
            MenuTree menuTree = new MenuTree();
            menuTree.setMenu(menu);
            if (menu.getParentId().equals("0")) {
                list.add(menuTree);
            }

            // 排除掉按钮
            if (menu.getType() != 2) {
                map.put(menu.getMenuId(), menuTree);
            }
        }
        return getMenuTrees(map, list);
    }

    @Override
    public List<MenuTree> allTreeMenu() {
        List<Menu> menus = menuMapper.getMenus();
        Map<String, MenuTree> map = new HashMap<>();
        List<MenuTree> list = new ArrayList<>();
        // 将数据全部放在map集合中
        for (Menu menu : menus) {
            MenuTree menuTree = new MenuTree();
            menuTree.setMenu(menu);
            if (menu.getParentId().equals("0")) {
                list.add(menuTree);
            }
            map.put(menu.getMenuId(), menuTree);
        }
        return getMenuTrees(map, list);
    }

    @Override
    public List<String> getTreeMenuByRoleId(String roleId) {
        List<UserRoleVo> menusByRoleId = menuMapper.getMenusByRoleId(roleId);
        // 获取所有id
        List<String> menus = new ArrayList<>(menusByRoleId.stream().map(UserRoleVo::getMenuId).toList());
        List<String> parentIds = menusByRoleId.stream().map(UserRoleVo::getParentId).toList();

        // 移除父id
        menus.removeAll(parentIds);
        return menus;
    }

    private List<MenuTree> getMenuTrees(Map<String, MenuTree> map, List<MenuTree> list) {
        for (Map.Entry<String, MenuTree> entry : map.entrySet()) {
            MenuTree value = entry.getValue();
            List<MenuTree> children;
            // 以父id为key如果能查到代表该节点有子节点
            String parentId = value.getMenu().getParentId();
            MenuTree parent = map.get(parentId);
            if (parent != null) {
                children = parent.getChildren();
                if (children == null) {
                    children = new ArrayList<>();
                    parent.setChildren(children);
                }
                children.add(value);
            }
        }
        return list;
    }
}
