package com.krund.hotel.manage.dto;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private Result(int ret, String msg, T data, boolean success) {
        this.ret = ret;
        this.msg = msg;
        this.data = data;
        this.success = success;
    }
    private T data;
    private int ret;
    private String msg;
    private boolean success;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static <T> Result<T> success(String msg, T data){
        return new Result<>(1,msg,data,true);
    }
    public static <T> Result<T> success(String msg){
        return new Result<>(1,msg,null,true);
    }
    public static <T> Result<T> success(T data){
        return new Result<>(1,"执行成功！",data,true);
    }
    public static <T> Result<T> success(){
        return new Result<>(1,"执行成功",null,true);
    }
    public static <T> Result<T> error(String msg){
        return new Result<>(0,msg,null,false);
    }
    public static <T> Result<T> error(){
        return new Result<>(0,"执行失败",null,false);
    }
    public static <T> Result<T> systemError(String msg){
        return new Result<>(-99,msg,null,false);
    }
    public static <T> Result<T> systemError(){
        return new Result<>(-99,"系统内部错误",null,false);
    }
    public static <T> Result<T> custom(boolean success, Integer ret, String msg, T data){
        return new Result<>(ret,msg,data,success);
    }
}
