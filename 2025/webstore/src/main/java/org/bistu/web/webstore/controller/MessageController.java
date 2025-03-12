package org.bistu.web.webstore.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MessageController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/test/info")
    public String home(@RequestParam(value="key", required=false) String key) {
        String message = messageSource.getMessage("test.message.key", null, new Locale("zh", "CN"));

        return message;
    }

    @GetMapping("/test/param")
    public String homeParam(@RequestParam("key") String key) {
        String message = messageSource.getMessage("test.message.cn.key", new String[] {key}, new Locale("zh", "CN"));

        return message;
    }
}