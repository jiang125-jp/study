package com.jp.test.regist2.api;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class regist2Api {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello！I'm a. port：8082";
    }

    @RequestMapping("/name")
    public String name(String name) {
        return "My name is " + name + ". aaa";
    }

    @RequestMapping("/age")
    public String age(String age) {
        return "I am " + age + " years old this year. aaa";
    }

    @RequestMapping("/routeAll")
    public String routeAll(String pass) {
        return "Can I pass? " + pass + "! port：8082";
    }

}
