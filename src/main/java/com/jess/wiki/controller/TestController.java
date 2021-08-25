package com.jess.wiki.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/8/25
 */
@RestController //返回字符串 RestController = Controller + ResponseBody
// @Controller返回页面
public class TestController {

    /**
     * 请求方式：GET POST PUT DELETE
     * RequestMapping表示接受所有方式的请求
     * @return 返回Hello World!字符串
     */
    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    @PostMapping("/hello/post")
    public String helloPost(String name){
        return "Hello World! Post" + name;
    }
}
