package org.wms.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.wms.common.entity.sys.Menu;
import org.wms.common.entity.sys.Role;
import org.wms.sys.entity.MenuRole;
import org.wms.sys.entity.UserRole;
import org.wms.sys.mapper.MenuMapper;
import org.wms.sys.mapper.RoleMapper;
import org.wms.sys.mapper.UserRoleMapper;
import org.wms.sys.service.MenuRoleService;
import org.wms.sys.service.RoleService;
import org.wms.sys.tools.AuthTools;

import java.util.*;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

    @Resource
    UserRoleMapper userRoleMapper;

    @Resource
    MenuRoleService menuRoleService;

    @Resource
    MenuMapper menuMapper;

    @Resource
    AuthTools authTools;


    @Override
    public boolean allotPermissions(List<String> permissions, String roleId) {
        // 删除当前用户的所有权限
        LambdaUpdateWrapper<MenuRole> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(MenuRole::getRoleId, roleId);
        boolean remove = menuRoleService.remove(wrapper);
        if (Objects.isNull(permissions) || permissions.isEmpty()) {
            return remove;
        }

        // 添加当前的用户权限
        permissions = addParent(permissions);
        permissions = addParent(permissions);
        // 添加权限
        List<MenuRole> menuRoles = new ArrayList<>();
        for (String menId : permissions) {
            MenuRole menuRole = new MenuRole();
            menuRole.setRoleId(roleId);
            menuRole.setMenuId(menId);
            menuRoles.add(menuRole);
        }

        // 清除token
        // 获取当前角色的所有用户
        LambdaQueryWrapper<UserRole> userRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userRoleLambdaQueryWrapper.eq(UserRole::getRoleId, roleId);
        List<String> roleList = userRoleMapper.selectList(userRoleLambdaQueryWrapper).stream().map(UserRole::getUserId).toList();
        authTools.deleteByUserId(roleList);
        return menuRoleService.saveBatch(menuRoles);
    }

    private List<String> addParent(List<String> child) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Menu::getParentId)
                .in(Menu::getMenuId, child);
        List<String> collect = menuMapper.selectList(wrapper).stream().map(Menu::getParentId).toList();

        Set<String> per = new HashSet<>(child);
        Set<String> parent = new HashSet<>(collect);
        List<String> list = new ArrayList<>(child);
        for (String item : parent) {
            if (!per.contains(item) && !"0".equals(item)) {
                list.add(item);
            }
        }
        return list;
    }
}
