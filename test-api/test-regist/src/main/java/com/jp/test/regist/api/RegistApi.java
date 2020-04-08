package com.jp.test.regist.api;

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

    @GetMapping("/age")
    public String age(String age) {
        try{
            Thread.sleep(10000);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return "I am " + age + " years old this year. aaa";
    }

    @PostMapping("/age2")
    public String age2() {
        try{
            Thread.sleep(10000);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return "I am age2 years old this year. aaa";
    }

    @GetMapping("/routeAll/{pass}")
    public String routeAll(@PathVariable String pass) {
        return "Can I pass? " + pass + "! port：8081";
    }

}
