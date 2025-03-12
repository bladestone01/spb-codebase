package org.bistu.web.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.bistu.web.dao.mapper")
public class MyBatisConfig {
}
