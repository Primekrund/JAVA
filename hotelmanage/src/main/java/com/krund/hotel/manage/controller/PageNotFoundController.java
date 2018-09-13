package com.krund.hotel.manage.controller;

import com.krund.hotel.manage.dto.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/error/")
public class PageNotFoundController {
    /**
    * @Description:  404处理
    * @Param: []
    * @return: com.krund.hotel.manage.dto.Result<java.lang.Object>
    * @Author: Zhang Ziming
    * @Date: 2018/4/12
    */
    @RequestMapping(value = "pagenotfound",method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> pageNotFound()
    {
        return Result.error("访问的路径不存在！请参阅API文档检查访问路径！");
    }
}
