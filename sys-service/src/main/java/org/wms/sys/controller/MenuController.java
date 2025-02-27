package org.wms.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.wms.common.entity.Menu;
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
     * 得到所有的非按钮id
     *
     * @return
     */
    @GetMapping("/getContent")
    @PreAuthorize("hasAuthority('sys:menu:list')")
    public Result<List<MenuTree>> getContent() {
        List<MenuTree> treeMenu = menuService.getTreeMenu(null);
        return Result.success(treeMenu, "查询成功");
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

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('sys:menu:add')")
    public Result<String> addMenu(@RequestBody Menu menu) {
        if (menu.getParentId() == null) {
            menu.setParentId("0");
        }
        boolean save = menuService.save(menu);
        if (!save) {
            return Result.error(null, "添加失败");
        }
        return Result.success(null, "添加成功");
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('sys:menu:update')")
    public Result<String> updateMenu(@RequestBody Menu menu, @PathVariable String id) {
        menu.setMenuId(id);
        // 判断父菜单
        if (menu.getParentId() == null) {
            menu.setParentId("0");
        }
        boolean save = menuService.updateById(menu);
        if (!save) {
            return Result.error(null, "修改失败");
        }
        return Result.success(null, "修改成功");
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('sys:menu:delete')")
    public Result<String> deleteMenu(@PathVariable String id) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getParentId, id);
        List<Menu> list = menuService.list(wrapper);
        if (!list.isEmpty()) {
            return Result.error(null, "该菜单拥有子菜单,无法删除");
        }
        menuService.removeById(id);
        return Result.success(null, "删除成功");
    }
}
