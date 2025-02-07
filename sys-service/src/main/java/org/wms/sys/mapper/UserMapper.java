package org.wms.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.wms.common.entity.User;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据userId获取当前用户的权限信息
     * @param userId id
     * @return List
     */
    List<String> getAuthorities(String userId);
}




