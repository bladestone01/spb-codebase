package org.bistu.web.webstore.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.bistu.web.webstore.dao.mapper")
public class MyBatisConfig {
}
