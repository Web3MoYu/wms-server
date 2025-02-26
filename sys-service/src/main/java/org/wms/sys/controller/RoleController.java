package org.wms.sys.controller;

import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wms.common.entity.Role;
import org.wms.common.model.Result;
import org.wms.sys.mapper.RoleMapper;
import org.wms.sys.service.RoleService;

import java.util.List;

@RequestMapping("/sys/role")
@RestController
public class RoleController {


    @Resource
    RoleMapper roleMapper;

    @Resource
    RoleService roleService;

    /**
     * 查询当前用户是什么角色
     *
     * @param id
     * @return
     */
    @GetMapping("/list/{id}")
    @PreAuthorize("hasAuthority('sys:role:index')")
    public Result<Role> getRoleById(@PathVariable("id") String id) {
        Role role = roleMapper.getRoleByUserID(id);
        return Result.success(role, "查询成功");
    }

    /**
     * 查询所有角色
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:role:index')")
    public Result<List<Role>> list() {
        return Result.success(roleService.list(), "查询成功");
    }
}
