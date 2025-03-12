package org.bistu.web.webstore.config;

import lombok.Data;
import org.bistu.web.webstore.global.ext.YamlPropertySourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@PropertySource(value = "classpath:config/systemconfig.yml", factory = YamlPropertySourceFactory.class)
@Configuration
public class SystemConfig {
    @Value("${base.config.myname}")
    private String myName;

    @Value("${base.boot.value}")
    private Integer value;
}
