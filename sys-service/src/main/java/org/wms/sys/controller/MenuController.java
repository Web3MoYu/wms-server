package org.wms.sys.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wms.common.entity.MenuTree;
import org.wms.common.model.Result;
import org.wms.sys.service.MenuService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/sys/menu")
public class MenuController {

    @Resource
    MenuService menuService;

    /**
     * 查询当前用户的权限信息并返回
     *
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:menu:list') or hasAuthority('sys:role:list')")
    public Result<List<MenuTree>> getMenuTree() {
        return Result.success(menuService.allTreeMenu(), "查询成功");
    }

    /**
     * 获取改角色的菜单的id用于回显菜单数据
     *
     * @param id
     * @return
     */
    @GetMapping("/list/{id}")
    @PreAuthorize("hasAuthority('sys:menu:list') or hasAuthority('sys:role:list')")
    public Result<List<String>> getMenuTreeById(@PathVariable String id) {
        return Result.success(menuService.getTreeMenuByRoleId(id), "查询成功");
    }
}
