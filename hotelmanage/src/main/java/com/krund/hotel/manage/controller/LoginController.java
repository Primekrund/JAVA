package com.krund.hotel.manage.controller;

import com.krund.hotel.manage.annotation.RequestLimit;
import com.krund.hotel.manage.dto.Result;
import com.krund.hotel.manage.entity.Manager;
import com.krund.hotel.manage.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: ihotel
 * @description: 登录控制器
 * @author: Zhang Ziming
 * @create: 2018-05-09 16:39
 **/
@Controller
@RequestMapping("/api/")
@Api(value = "登录控制器",description  = "登录控制器")
public class LoginController {
    @Resource
    private LoginService loginService;

    @RequestLimit //请求频率限制
    @RequestMapping(value = "token",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Manager用户登录", notes = "Manager用户登录")
    public Result<String> login(@ApiParam(name = "username",value = "用户名",required = true) @RequestParam String username, @ApiParam(name = "password",value = "密码",required = true) @RequestParam String password, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        return loginService.login(username,password,request,response);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "token",method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "Manager用户登出", notes = "Manager用户登出")
    public Result<Object> logout(HttpServletRequest request, @ApiParam(name = "token",value = "token",required = true) @RequestParam String token, HttpServletResponse response) throws Exception
    {
        return loginService.logout(request, token,response);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "/openid",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "小程序注册登录", notes = "小程序注册登录")
    public Result<String> login(HttpServletRequest request, @ApiParam(name = "jscode",value = "js_code",required = true) @RequestParam String jscode, HttpServletResponse response) throws Exception {
        return loginService.login(jscode,response);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "/manager",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获得用户信息", notes = "获得用户信息")
    public Result<Manager> getManagerByToken(HttpServletRequest request, @ApiParam(name = "token",value = "token",required = true) @RequestParam String token, HttpServletResponse response) throws Exception {
        return loginService.getManagerByToken(token);
    }
}
