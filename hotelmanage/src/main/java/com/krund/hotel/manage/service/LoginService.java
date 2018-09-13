package com.krund.hotel.manage.service;

import com.krund.hotel.manage.dto.Result;
import com.krund.hotel.manage.entity.Manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {
    Result<String> login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws Exception;
    /**
     * 微信小程序注册登录
     * @param jsCode
     * @return
     */
    Result<String> login(String jsCode, HttpServletResponse response) throws Exception;

    /** 
    * @Description: 登出 
    * @Param: [token, response] 
    * @return: com.krund.hotel.manage.dto.Result<java.lang.String>
    * @Author: Zhang Ziming
    * @Date: 2018/5/10 
    */ 
    Result<Object> logout(HttpServletRequest request, String token, HttpServletResponse response) throws Exception;

    Result<Manager> getManagerByToken(String token) throws Exception;
}
