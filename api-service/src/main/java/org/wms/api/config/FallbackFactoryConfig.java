package org.wms.api.config;

import org.springframework.context.annotation.Bean;
import org.wms.api.client.fallback.MenuClientFallback;
import org.wms.api.client.fallback.UserClientFallback;

public class FallbackFactoryConfig {
    @Bean
    public MenuClientFallback menuClientFallback() {
        return new MenuClientFallback();
    }

    @Bean
    public UserClientFallback userClientFallback() {
        return new UserClientFallback();
    }
}
