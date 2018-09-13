package com.krund.hotel.manage.exception;

/**
 * @program: ihotel
 * @description: 请求过于频繁异常
 * @author: Zhang Ziming
 * @create: 2018-05-10 15:43
 **/
public class RequestLimitException extends Exception {

    public RequestLimitException() {
        super("请求过于频繁,请稍后再试");
    }

    public RequestLimitException(String message) {
        super(message);
    }
}
