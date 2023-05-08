package com.switchsky.config.sensitive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SensitiveConfig {
    @Bean
    public SensitiveUtil getSensitiveWordMap() {
        SensitiveUtil sensitiveUtil = new SensitiveUtil();
        sensitiveUtil.initContext();
        return sensitiveUtil;
    }
}
