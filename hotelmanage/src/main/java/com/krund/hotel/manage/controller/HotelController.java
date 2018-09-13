package com.krund.hotel.manage.controller;

import com.krund.hotel.manage.annotation.RequestLimit;
import com.krund.hotel.manage.dto.Result;
import com.krund.hotel.manage.entity.Hotel;
import com.krund.hotel.manage.service.HotelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @program: ihotel
 * @description: 酒店控制器
 * @author: Zhang Ziming
 * @create: 2018-05-10 14:53
 **/
@Controller
@RequestMapping("/api/")
@Api(value = "酒店控制器",description  = "酒店控制器")
public class HotelController {
    @Resource
    private HotelService hotelService;

    @RequestLimit //请求频率限制
    @RequestMapping(value = "hotels",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "创建一个Hotel对象")
    public Result<Object> add(HttpServletRequest request, @ModelAttribute Hotel hotel, HttpServletResponse response) throws Exception{
        return hotelService.insertOrUpdate(hotel,response);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "hotels/update",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更新Hotel对象 提供全部字段")
    public Result<Object> put(HttpServletRequest request, @ModelAttribute Hotel hotel, HttpServletResponse response) throws Exception{
        return hotelService.insertOrUpdate(hotel,response);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "hotels",method = RequestMethod.PATCH)
    @ResponseBody
    @ApiOperation(value = "更新Hotel对象 提供部分字段")
    public Result<Object> patch(HttpServletRequest request, @ModelAttribute Hotel hotel, HttpServletResponse response) throws Exception{
        return hotelService.insertOrUpdate(hotel,response);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "hotels/{id}",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获得Hotel对象")
    public Result<Hotel> getById(HttpServletRequest request, @ApiParam(name = "id",value = "id",required = true) @PathVariable("id") Integer id) throws Exception
    {
        return hotelService.getById(id);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "hotels",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获得所有Hotel对象")
    public Result<List<Hotel>> getAllByMid(HttpServletRequest request) throws Exception
    {
        return hotelService.getAllByMid(request);
    }
}
