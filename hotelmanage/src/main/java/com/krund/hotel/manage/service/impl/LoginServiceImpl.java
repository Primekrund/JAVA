package com.krund.hotel.manage.service.impl;

import com.krund.hotel.manage.component.JwtUtil;
import com.krund.hotel.manage.dao.ManagerDao;
import com.krund.hotel.manage.dto.Result;
import com.krund.hotel.manage.entity.Manager;
import com.krund.hotel.manage.entity.Token;
import com.krund.hotel.manage.service.LoginService;
import com.krund.hotel.manage.service.TokenService;
import com.krund.hotel.manage.util.HttpClientUtil;
import com.krund.hotel.manage.util.IpUtil;
import io.jsonwebtoken.Claims;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @program: ihotel
 * @description: 登录业务逻辑
 * @author: Zhang Ziming
 * @create: 2018-05-10 09:36
 **/
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private ManagerDao managerDao;
    @Resource
    private TokenService tokenService;
    @Resource
    private JwtUtil jwtUtil;

    private static final String APP_ID = "wxba37fc90d97165ec";
    private static final String SECRET = "3e36d0bd92e46ff7571f263f7f4a7bf2";

    @Override
    public Result<String> login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws Exception{
        Manager manager = managerDao.login(username,password);
        if(manager != null){
            String token = jwtUtil.generToken(String.valueOf(manager.getId()),"www.krund.com","iHotel-login");
            Token t = new Token();
            t.setHotelId(manager.getId());
            t.setToken(token);
            t.setValidity(jwtUtil.getValidity());
            t.setIp(IpUtil.getIpAddr(request));
            tokenService.insertOrUpdate(t);
            response.setHeader("token",token);
            return Result.success("登陆成功",token);
        }
        response.setStatus(401); //表示用户没有权限（令牌、用户名、密码错误）
        return Result.error("登录失败，用户名或密码错误");
    }

    @Override
    public Result<String> login(String jsCode, HttpServletResponse response) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("https://api.weixin.qq.com/sns/jscode2session")
                .append("?appid=")
                .append(APP_ID)
                .append("&secret=")
                .append(SECRET)
                .append("&js_code=")
                .append(jsCode)
                .append("&grant_type=authorization_code");
        String jsonStr =  HttpClientUtil.get(sb.toString());
        JSONObject jo = JSONObject.fromObject(jsonStr);
        if(jo.containsKey("openid")){
            String openId = jo.getString("openid");
            Manager manager = managerDao.getByOpenId(openId);
            if(manager == null){
                manager = new Manager();
                manager.setOpenid(openId);
                managerDao.insert(manager);
                return Result.success("新注册用户，登录成功！",openId);
            }
            return Result.success("登录成功！",openId);
        }
        response.setStatus(401); //表示用户没有权限（令牌、用户名、密码错误）
        return Result.error("微信返回错误消息：" + jo.getString("errmsg"));
    }

    @Override
    public Result<Object> logout(HttpServletRequest request, String token, HttpServletResponse response) throws Exception{
        Claims claims = jwtUtil.verifyToken(token);
        String s = jwtUtil.updateToken(token);
        Token t = new Token();
        t.setHotelId(Integer.parseInt(claims.getId()));
        t.setValidity(new Date());
        tokenService.insertOrUpdate(t);
        if(!s.equals(token))
            return Result.success("退出登录成功！");
        return Result.error("退出登录失败！");
    }

    @Override
    public Result<Manager> getManagerByToken(String token) throws Exception {
        Manager manager = managerDao.getByToken(token);
        if (manager == null)
            return Result.error("用户不存在！");
        return Result.success(manager);
    }
}
