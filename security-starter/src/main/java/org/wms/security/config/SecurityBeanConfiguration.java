package org.wms.security.config;

import org.springframework.context.annotation.Configuration;
import org.wms.security.filter.TokenFilter;
import org.wms.security.handler.SecurityExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

/**
 * SpringSecurity配置类
 */
@Configuration
public class SecurityBeanConfiguration {
    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }

    @Bean
    @Order(-1) //提升优先级
    public SecurityExceptionHandler securityExceptionHandler() {
        return new SecurityExceptionHandler();
    }
}
