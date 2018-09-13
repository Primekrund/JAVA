package com.krund.hotel.manage.util;

/**
 * @program: ihotel
 * @description: 自定义字符串处理工具
 * @author: Zhang Ziming
 * @create: 2018-05-10 18:21
 **/
public class MyStringUtils {
    private MyStringUtils(){}
    
    /** 
    * @Description: 拼接字符串 
    * @Param: [args] 
    * @return: java.lang.String 
    * @Author: Zhang Ziming
    * @Date: 2018/5/10 
    */ 
    public static synchronized String concat(String... args){
        StringBuilder sb = new StringBuilder();
        for (String str:
             args) {
            sb.append(str);
        }
        return sb.toString();
    }
}
