package org.wms.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.wms.common.entity.Role;

import java.util.List;


public interface RoleService extends IService<Role> {

    boolean allotPermissions(List<String> permissions, String roleId);
}
