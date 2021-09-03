package com.jess.wiki.controller;

import com.jess.wiki.req.UserLoginReq;
import com.jess.wiki.req.UserQueryReq;
import com.jess.wiki.req.UserResetPasswordReq;
import com.jess.wiki.req.UserSaveReq;
import com.jess.wiki.resp.CommonResp;
import com.jess.wiki.resp.UserLoginResp;
import com.jess.wiki.resp.UserQueryResp;
import com.jess.wiki.resp.PageResp;
import com.jess.wiki.service.UserService;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/8/25
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

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
        resp.setContent(userLoginResp);
        return resp;
    }
}