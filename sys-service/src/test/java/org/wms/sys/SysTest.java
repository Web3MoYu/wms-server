package org.wms.sys;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.wms.sys.mapper.RoleMapper;

@SpringBootTest
public class SysTest {

    @Resource
    RoleMapper roleMapper;

    @Test
    public void contextLoads() {
        System.out.println(roleMapper.selectList(null));
    }
}
