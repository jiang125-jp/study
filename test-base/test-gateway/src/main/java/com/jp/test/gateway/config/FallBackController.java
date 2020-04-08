package com.jp.test.gateway.config;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

    @RequestMapping("/fallback")
    public String fallback() {
        return "I'm Spring Cloud8000 Gateway fallback.";
    }
}
