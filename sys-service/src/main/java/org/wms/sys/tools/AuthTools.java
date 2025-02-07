package org.wms.sys.tools;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.wms.common.utils.RedisUtils;

import java.util.List;
import java.util.Objects;

@Component
public class AuthTools {

    @Resource
    RedisTemplate<String, Objects> redisTemplate;

    public void deleteByUserId(List<Long> ids) {
        for (Long id : ids) {
            redisTemplate.delete(RedisUtils.TOKEN_KEY + id);
            redisTemplate.delete(RedisUtils.PERMISSIONS_KEY + id);
        }
    }
}
