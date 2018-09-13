package com.krund.hotel.manage.controller;

import com.krund.hotel.manage.dto.Result;
import com.krund.hotel.manage.util.MyStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: iHotel
 * @description: 异常处理控制器，对所有Controller生效，应用到所有@RequestMapping注解的方法，在其抛出UnauthenticatedException异常时执行,
 *               要返回到前端的异常消息不捕获全部抛出到Controller即可
 * @author: Zhang Ziming
 * @create: 2018-04-12 17:43
 **/
@ControllerAdvice
public class WebExceptionHandler {
    //@Resource
    //private ErrorLogService errorLogService;

    @ExceptionHandler
    @ResponseBody
    public Result<Object> handleAndReturnData(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        //ErrorLog errorLog = new ErrorLog();
        //errorLog.setMsg(ex.getMessage());
        //errorLog.setUri(request.getRequestURI());
        //errorLogService.insert(errorLog);
        response.setStatus(200);
        String message =  ex.getMessage();
        if(StringUtils.isEmpty(message))
            message = ex.getCause().getMessage();
        return Result.systemError(MyStringUtils.concat("服务器处理请求时出现了异常，具体异常信息为：", message, "  请求的URI为：", request.getRequestURI()));
    }
}