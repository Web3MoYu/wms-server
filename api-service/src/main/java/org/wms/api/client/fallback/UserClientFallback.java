package org.wms.api.client.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.wms.api.client.UserClient;
import org.wms.common.entity.User;

import java.util.List;

/**
 * UserClient 兜底策略
 */
public class UserClientFallback implements FallbackFactory<UserClient> {
    private static final Logger log = LoggerFactory.getLogger(UserClientFallback.class);

    @Override
    public UserClient create(Throwable cause) {
        return new UserClient() {
            @Override
            public User getUserByUserName(String username) {
                log.warn("触发兜底,方法:getUserByUserName, username={}", username);
                return new User();
            }

            @Override
            public User getUserById(String userId) {
                log.warn("触发兜底,方法:getUserByUserId, userId={}", userId);
                return new User();
            }

            @Override
            public User getUserByEmail(String email) {
                log.warn("触发兜底,方法:getUserByUserEmail, email={}", email);
                return new User();
            }

            @Override
            public User getUserByPhone(String phone) {
                log.warn("触发兜底,方法:getUserByUserPhone, phone={}", phone);
                return new User();
            }

            @Override
            public User getUserByWxId(String wxId) {
                log.warn("触发兜底,方法:getUserByWxId, wxId={}", wxId);
                return new User();
            }

            @Override
            public List<String> getAuthoritiesByUserId(String userId) {
                log.warn("触发兜底,方法:getAuthoritiesByUserId, userId={}", userId);
                return List.of();
            }
        };
    }
}
