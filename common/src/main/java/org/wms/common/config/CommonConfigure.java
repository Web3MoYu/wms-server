package org.wms.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.wms.common.handler.CommonExceptionHandler;

@AutoConfiguration
public class CommonConfigure {
    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public CommonExceptionHandler commonExceptionHandler() {
        return new CommonExceptionHandler(applicationName);
    }
}
