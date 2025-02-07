package org.wms.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wms.sys.entity.MenuRole;
import org.wms.sys.mapper.MenRoleMapper;
import org.wms.sys.service.MenuRoleService;

@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenRoleMapper, MenuRole>
        implements MenuRoleService {
}
