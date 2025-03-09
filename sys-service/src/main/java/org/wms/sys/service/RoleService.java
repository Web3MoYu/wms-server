package org.wms.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.wms.common.entity.Role;

import java.util.List;


public interface RoleService extends IService<Role> {

    /**
     * 分配权限
     *
     * @param permissions 权限
     * @param roleId      角色id
     * @return 结果
     */
    boolean allotPermissions(List<String> permissions, String roleId);
}
