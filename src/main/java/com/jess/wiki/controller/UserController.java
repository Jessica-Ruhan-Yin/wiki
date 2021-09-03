package com.jess.wiki.controller;

import com.alibaba.fastjson.JSONObject;
import com.jess.wiki.req.UserLoginReq;
import com.jess.wiki.req.UserQueryReq;
import com.jess.wiki.req.UserResetPasswordReq;
import com.jess.wiki.req.UserSaveReq;
import com.jess.wiki.resp.CommonResp;
import com.jess.wiki.resp.UserLoginResp;
import com.jess.wiki.resp.UserQueryResp;
import com.jess.wiki.resp.PageResp;
import com.jess.wiki.service.UserService;
import com.jess.wiki.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/8/25
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private SnowFlake snowFlake;

    @GetMapping("/list")
    public CommonResp list(@Valid UserQueryReq req) {
        CommonResp<PageResp<UserQueryResp>> resp = new CommonResp<>();
        PageResp<UserQueryResp> list = userService.list(req);
        resp.setContent(list);
        return resp;
    }

    //post请求，json方式的提交 需要增加@RequestBody注解才可以接收到
    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody UserSaveReq req) {
        //十六进制MD5 加密密码
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
        userService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>();
        userService.delete(id);
        return resp;
    }

    @PostMapping("/reset-password")
    public CommonResp resetPassword(@Valid @RequestBody UserResetPasswordReq req) {
        //十六进制MD5 加密密码
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
        userService.resetPassword(req);
        return resp;
    }

    @PostMapping("/login")
    public CommonResp login(@Valid @RequestBody UserLoginReq req) {
        //十六进制MD5 加密密码
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp<UserLoginResp> resp = new CommonResp<>();
        UserLoginResp userLoginResp = userService.login(req);

        Long token = snowFlake.nextId();
        LOG.info("生成单点登录token:{}，并放入redis中",token);
        userLoginResp.setToken(token.toString());
        redisTemplate.opsForValue().set(token, JSONObject.toJSONString(userLoginResp),3600*24, TimeUnit.SECONDS);
        resp.setContent(userLoginResp);
        return resp;
    }
}