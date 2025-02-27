package org.wms.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.wms.common.entity.Role;
import org.wms.common.exception.BizException;
import org.wms.common.model.Result;
import org.wms.sys.entity.UserRole;
import org.wms.sys.mapper.RoleMapper;
import org.wms.sys.mapper.UserRoleMapper;
import org.wms.sys.service.RoleService;

import java.util.List;

@RequestMapping("/sys/role")
@RestController
public class RoleController {


    @Resource
    RoleMapper roleMapper;

    @Resource
    RoleService roleService;

    @Resource
    UserRoleMapper userRoleMapper;

    /**
     * 查询当前用户是什么角色
     *
     * @param id
     * @return
     */
    @GetMapping("/list/{id}")
    @PreAuthorize("hasAuthority('sys:role:list')")
    public Result<Role> getRoleById(@PathVariable("id") String id) {
        Role role = roleMapper.getRoleByUserID(id);
        return Result.success(role, "查询成功");
    }

    /**
     * 查询所有角色
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:role:list')")
    public Result<List<Role>> list() {
        return Result.success(roleService.list(), "查询成功");
    }


    /**
     * 搜索查询所有角色
     */
    @GetMapping("/page")
    @PreAuthorize("hasAuthority('sys:role:list')")
    public Result<Page<Role>> pageSearch(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize, @RequestParam(required = false) String roleName) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(roleName != null, Role::getRoleName, roleName);
        Page<Role> result = roleService.page(new Page<>(page, pageSize), wrapper);
        return Result.success(result, "查询成功");
    }

    /**
     * 添加角色信息
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('sys:role:add')")
    public Result<String> addRole(@RequestBody Role role) {
        boolean save = roleService.save(role);
        if (!save) {
            throw new BizException("添加失败");
        }
        return Result.success(null, "添加成功");
    }

    /**
     * 编辑角色信息
     */
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('sys:role:update')")
    public Result<String> updateRole(@RequestBody Role role, @PathVariable String id) {
        LambdaUpdateWrapper<Role> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(id != null, Role::getRoleId, id);
        boolean update = roleService.update(role, wrapper);
        if (!update) {
            throw new BizException("修改失败");
        }
        return Result.success(null, "修改成功");
    }

    /**
     * 删除角色信息
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    public Result<String> deleteRole(@PathVariable String id) {
        // 查询当前角色是否被使用
        LambdaUpdateWrapper<UserRole> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(UserRole::getRoleId, id);
        List<UserRole> userRole = userRoleMapper.selectList(wrapper);
        if (!userRole.isEmpty()) {
            throw new BizException("无法删除，正在被使用");
        }
        boolean b = roleService.removeById(id);
        if (!b) {
            throw new BizException("删除失败");
        }
        return Result.success(null, "删除成功");
    }

    @Transactional
    @PutMapping("/updatePermissions/{roleId}")
    @PreAuthorize("hasAuthority('sys:role:allot')")
    public Result<String> allotPermissions(@PathVariable String roleId, @RequestBody List<String> permissions) {
        boolean per = roleService.allotPermissions(permissions, roleId);
        if (!per) {
            throw new BizException("分配失败");
        }
        return Result.success(null, "分配成功");
    }
}
