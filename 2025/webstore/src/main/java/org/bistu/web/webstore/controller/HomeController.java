package org.bistu.web.webstore.controller;

import lombok.extern.slf4j.Slf4j;
import org.bistu.web.webstore.config.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HomeController {
    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private SystemConfig systemConfig;

    @GetMapping("/hello")
    public String getName() {
        log.info("Hello Info:{}", appName);

        return appName;
    }

    @GetMapping("/myname")
    public String getMyName() {
        log.info("Hello Info:{}", this.systemConfig.getMyName());

        return this.systemConfig.getMyName();
    }
}
