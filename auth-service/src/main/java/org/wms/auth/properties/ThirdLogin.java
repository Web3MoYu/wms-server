package org.wms.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "uniqueker")
public class ThirdLogin {
    private String appId;
    private String appKey;
    private String redirectUrl;
}