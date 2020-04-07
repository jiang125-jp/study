package com.jp.test.regist.api;

import com.jp.test.regist.bean.Monkey;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistApi {

    @GetMapping("/hello")
    public String hello() {
        return "Hello！I'm a. port：8081";
    }

    @GetMapping("/name/{n}")
    public String name(@PathVariable String n) {
        return "My name is :" + n;
    }

    @PostMapping("/age")
    public String age(@RequestBody Monkey monkey) {
        return "I am " + monkey.getName() + ",I am " + monkey.getAge() + " years old this year";
    }

    @GetMapping("/routeAll/{pass}")
    public String routeAll(@PathVariable String pass) {
        return "Can I pass? " + pass + "! port：8081";
    }

}
