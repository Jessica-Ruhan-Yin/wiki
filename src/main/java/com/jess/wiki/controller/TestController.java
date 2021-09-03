package com.jess.wiki.controller;

import com.jess.wiki.domain.Test;
import com.jess.wiki.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/8/25
 */
@RestController //返回字符串 RestController = Controller + ResponseBody
// @Controller返回页面
public class TestController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private TestService testService;

    //取自定义配置项的值
    //默认值：@Value("${test.hello:TEST}")
    @Value("${test.hello}")
    private String testHello;

    /**
     * 请求方式：GET POST PUT DELETE
     * RequestMapping表示接受所有方式的请求
     * @return 返回Hello World!字符串
     */
    @GetMapping("/hello")
    public String hello(){
        return "Hello World!" + testHello;
    }

    @PostMapping("/hello/post")
    public String helloPost(String name){
        return "Hello World! Post" + name;
    }

    @GetMapping("/test/list")
    public List<Test> list(){
       return testService.list();
    }

    @RequestMapping("/redis/set/{key}/{value}")
    public String set(@PathVariable Long key, @PathVariable String value) {
        redisTemplate.opsForValue().set(key, value, 3600, TimeUnit.SECONDS);
        LOG.info("key: {}, value: {}", key, value);
        return "success";
    }

    @RequestMapping("/redis/get/{key}")
    public Object get(@PathVariable Long key) {
        Object object = redisTemplate.opsForValue().get(key);
        LOG.info("key: {}, value: {}", key, object);
        return object;
    }
}
