package com.krund.hotel.manage.controller;

import com.krund.hotel.manage.annotation.RequestLimit;
import com.krund.hotel.manage.dto.Result;
import com.krund.hotel.manage.entity.Payorder;
import com.krund.hotel.manage.service.PayorderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @program: ihotel
 * @description: 账务控制器
 * @author: Zhang Ziming
 * @create: 2018-05-14 11:36
 **/
@Controller
@RequestMapping("/api/")
@Api(value = "账务控制器",description  = "账务控制器")
public class PayorderController {
    @Resource
    private PayorderService payorderService;

    @RequestLimit //请求频率限制
    @RequestMapping(value = "payorders",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "创建一个Payorder对象")
    public Result<Object> add(HttpServletRequest request, @ModelAttribute Payorder payorder, HttpServletResponse response) throws Exception{
        return payorderService.insertOrUpdate(request, payorder,response);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "payorders/searchpage",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页搜索Payorder对象")
    public Result<Map<String, Object>> searchSelectivePage(HttpServletRequest request, @ModelAttribute Payorder payorder, HttpServletResponse response) throws Exception{
        return payorderService.searchSelectivePage(payorder);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "payorders/analysis",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页搜索Payorder对象")
    public Result<List<Payorder>> analysis(HttpServletRequest request, @ModelAttribute Payorder payorder, HttpServletResponse response) throws Exception{
        return payorderService.analysis(payorder);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "payorders/pay",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "创建一个Payorder对象")
    public Result<Object> pay(HttpServletRequest request, @ModelAttribute Payorder payorder, HttpServletResponse response) throws Exception{
        return payorderService.pay(request, payorder,response);
    }

    //==============================================自助机接口========================================

    @RequestLimit //请求频率限制
    @RequestMapping(value = "device/payorders",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "创建一个Payorder对象")
    public Result<Object> addByDevice(HttpServletRequest request, @ModelAttribute Payorder payorder, HttpServletResponse response) throws Exception{
        return payorderService.insertOrUpdate(request, payorder,response);
    }
}
