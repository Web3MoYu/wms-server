package org.wms.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wms.common.entity.Role;
import org.wms.sys.mapper.RoleMapper;
import org.wms.sys.service.RoleService;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

}
