package com.krund.hotel.manage.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.krund.hotel.manage.component.JwtUtil;
import com.krund.hotel.manage.dto.Result;
import com.krund.hotel.manage.entity.Hotel;
import com.krund.hotel.manage.entity.Token;
import com.krund.hotel.manage.service.HotelService;
import com.krund.hotel.manage.service.TokenService;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Date;

public class HeaderTokenInterceptor implements HandlerInterceptor {

    private final static Logger logger = Logger.getLogger(HeaderTokenInterceptor.class);
    @Resource
    private JwtUtil jwtUtil;
    @Resource
    private TokenService tokenService;
    @Resource
    private HotelService hotelService;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String requestURI=httpServletRequest.getRequestURI();
        String token=httpServletRequest.getHeader("token");
        if(requestURI.contains("/api/")&&!requestURI.contains("token")&&!requestURI.contains("device")){
            if(token==null || "".equals(token)){
                String str = httpServletRequest.getParameter("token");
                if(str==null || "".equals(str)){
                    logger.debug("---------------缺少token--------------------");
                    dealErrorReturn(httpServletRequest,httpServletResponse,"缺少token，无法验证");
                    return false;
                }
                token = str;
            }
            //验证token
            Claims claims = jwtUtil.verifyToken(token);
            if(claims == null){
                dealErrorReturn(httpServletRequest,httpServletResponse,"验证失败，token不正确或者token已经过期，请重新登录");
                return false;
            }
            Integer id = Integer.parseInt(claims.getId());
            Date date = claims.getExpiration();
            if(!tokenService.verifyToken(id,token,date)){
                dealErrorReturn(httpServletRequest,httpServletResponse,"验证失败，token不正确或者token已经过期，请重新登录");
                return false;
            }
            //更新token有效时间
            Token t = new Token();
            t.setHotelId(id);
            t.setValidity(new Date(date.getTime() + 1800000));
            tokenService.insertOrUpdate(t);
            //设置新的token到Header
            httpServletResponse.setHeader("token",token);
            httpServletRequest.setAttribute("managerId",id);
        }
        if (requestURI.contains("device")){
            final Base64.Decoder decoder = Base64.getDecoder();
            String str = httpServletRequest.getHeader("appKey");
            if (StringUtils.isEmpty(str)){
                dealErrorReturn(httpServletRequest,httpServletResponse,"请提供appKey");
                return false;
            }
            if (str.length() < 4){
                dealErrorReturn(httpServletRequest,httpServletResponse,"appKey长度不够");
                return false;
            }
            StringBuffer stringBuffer = new StringBuffer(str);
            stringBuffer.replace(1,3,"");
            str = stringBuffer.toString();
            String appKey = new String(decoder.decode(str),"UTF-8");
            String []params = appKey.split("-");
            Hotel hotel = hotelService.getByHidMid(Integer.parseInt(params[1]),Integer.parseInt(params[0]));
            if (hotel == null){
                dealErrorReturn(httpServletRequest,httpServletResponse,"提供的账户信息不正确");
                return false;
            }
            httpServletRequest.setAttribute("params",params);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
    }

    // 检测到没有token，直接返回不验证
    public void dealErrorReturn(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String msg){
        String json = JSONObject.toJSONString(Result.custom(false,-1,msg,null));
        PrintWriter writer = null;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        try {
            writer = httpServletResponse.getWriter();
            writer.print(json);
            writer.flush();

        } catch (IOException ex) {
            logger.error("response error",ex);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

}
