package com.krund.hotel.manage.controller;

import com.krund.hotel.manage.annotation.RequestLimit;
import com.krund.hotel.manage.dto.Result;
import com.krund.hotel.manage.entity.Roomtype;
import com.krund.hotel.manage.service.RoomtypeService;
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
 * @description: 房型控制器
 * @author: Zhang Ziming
 * @create: 2018-05-11 10:49
 **/
@Controller
@RequestMapping("/api/")
@Api(value = "房型控制器",description  = "房型控制器")
public class RoomtypeController {
    @Resource
    private RoomtypeService roomtypeService;

    @RequestLimit //请求频率限制
    @RequestMapping(value = "roomtypes",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "创建一个Roomtype对象")
    public Result<Object> add(HttpServletRequest request, @ModelAttribute Roomtype roomtype, HttpServletResponse response) throws Exception{
        return roomtypeService.insertOrUpdate(request, roomtype,response);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "roomtypes/update",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更新Roomtype对象 提供全部字段")
    public Result<Object> put(HttpServletRequest request, @ModelAttribute Roomtype roomtype, HttpServletResponse response) throws Exception{
        return roomtypeService.insertOrUpdate(request, roomtype,response);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "roomtypes",method = RequestMethod.PATCH)
    @ResponseBody
    @ApiOperation(value = "更新Roomtype对象 提供部分字段")
    public Result<Object> patch(HttpServletRequest request, @ModelAttribute Roomtype roomtype, HttpServletResponse response) throws Exception{
        return roomtypeService.insertOrUpdate(request, roomtype,response);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "roomtypes/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除Room对象")
    public Result<Object> delete(HttpServletRequest request, @ApiParam(name = "id",value = "id",required = true) @PathVariable("id") Integer id) throws Exception
    {
        return roomtypeService.delete(id);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "roomtypes/hid/{hid}",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获得所有Roomtype对象,需要传酒店id")
    public Result<List<Roomtype>> getAllByHid(HttpServletRequest request,@ApiParam(name = "hid",value = "hid",required = true) @PathVariable("hid") Integer hid) throws Exception
    {
        return roomtypeService.getAllByHid(hid);
    }



    //==============================================自助机接口========================================

    /**
     * @Description: 根据酒店id获得所有房型信息
     * @Param: [hid]
     * @return: void
     * @Author: Zhang Ziming
     * @Date: 2018/5/16
     */
    @RequestLimit //请求频率限制
    @RequestMapping(value = "device/roomtypes",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "自助机接口:获得可用的房型")
    public Result<Object> getAllByHidDevice(HttpServletRequest request) throws Exception{
        return roomtypeService.getAllByHidByDevice(request);
    }
}
