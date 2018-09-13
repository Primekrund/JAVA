package com.krund.hotel.manage.controller;

import com.krund.hotel.manage.annotation.RequestLimit;
import com.krund.hotel.manage.dto.Result;
import com.krund.hotel.manage.entity.Room;
import com.krund.hotel.manage.service.RoomService;
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
 * @description: 房间控制器
 * @author: Zhang Ziming
 * @create: 2018-05-11 11:23
 **/
@Controller
@RequestMapping("/api/")
@Api(value = "房间控制器",description  = "房间控制器")
public class RoomController {
    @Resource
    private RoomService roomService;

    @RequestLimit //请求频率限制
    @RequestMapping(value = "rooms",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "创建一个Room对象")
    public Result<Object> add(HttpServletRequest request, @ModelAttribute Room room, HttpServletResponse response) throws Exception{
        return roomService.insertOrUpdate(request, room, response);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "rooms/update",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更新Room对象 提供全部字段")
    public Result<Object> put(HttpServletRequest request, @ModelAttribute Room room, HttpServletResponse response) throws Exception{
        return roomService.insertOrUpdate(request, room, response);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "rooms",method = RequestMethod.PATCH)
    @ResponseBody
    @ApiOperation(value = "更新Room对象 提供部分字段")
    public Result<Object> patch(HttpServletRequest request, @ModelAttribute Room room, HttpServletResponse response) throws Exception{
        return roomService.insertOrUpdate(request, room,response);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "rooms/{id}",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获得Room对象")
    public Result<Room> getById(HttpServletRequest request, @ApiParam(name = "id",value = "id",required = true) @PathVariable("id") Integer id) throws Exception
    {
        return roomService.getById(id);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "rooms/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除Room对象")
    public Result<Object> delete(HttpServletRequest request, @ApiParam(name = "id",value = "id",required = true) @PathVariable("id") Integer id) throws Exception
    {
        return roomService.delete(id);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "rooms",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获得所有Room对象")
    public Result<List<Room>> getAll(HttpServletRequest request,
                                     @ApiParam(name = "hid",value = "酒店id") Integer hid,
                                             @ApiParam(name = "status",value = "房态") Integer status,
                                             @ApiParam(name = "tid",value = "房型id") Integer tid,
                                             @ApiParam(name = "roomno",value = "房间号") String roomno) throws Exception
    {
        return roomService.getAll(request, hid, status, tid, roomno);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "rooms/lockroom/{id}/{minute}",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "锁房")
    public Result<Object> lockRoom(HttpServletRequest request,
                                   @ApiParam(name = "id",value = "id",required = true) @PathVariable("id") Integer id,
                                   @ApiParam(name = "minute",value = "minute",required = true) @PathVariable("minute") Integer minute,
                                   HttpServletResponse response) throws Exception{
        return roomService.lockRoom(id, minute);
    }

    //==============================================自助机接口========================================

    /** 
    * @Description: 根据房型id获得可用的房间
    * @Param: [tid] 
    * @return: void 
    * @Author: Zhang Ziming
    * @Date: 2018/5/16 
    */ 
    @RequestLimit //请求频率限制
    @RequestMapping(value = "device/rooms/usable/tid/{tid}",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "自助机接口:获得可用的房间")
    public Result<List<Room>> getUsableByTid(HttpServletRequest request, @ApiParam(name = "tid",value = "tid",required = true) @PathVariable("tid") Integer tid) throws Exception{
        return roomService.getUsableByTid(request, tid);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "device/rooms/getroomstatus",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "自助机接口:检查房间状态，只需传房间号")
    public Result<Byte> getRoomStatus(HttpServletRequest request, @ModelAttribute Room room) throws Exception{
        return roomService.getRoomStatus(request,room);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "device/rooms/lockroom/{id}/{minute}",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "自助机接口:锁房")
    public Result<Object> lockRoomByDevice(HttpServletRequest request,
                                   @ApiParam(name = "id",value = "id",required = true) @PathVariable("id") Integer id,
                                   @ApiParam(name = "minute",value = "minute",required = true) @PathVariable("minute") Integer minute) throws Exception{
        return roomService.lockRoom(id, minute);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "device/rooms/unlockroom/{id}",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "自助机接口:解锁房间")
    public Result<Object> unLockRoomByDevice(HttpServletRequest request,
                                           @ApiParam(name = "id",value = "id",required = true) @PathVariable("id") Integer id) throws Exception{
        return roomService.unLockRoom(id);
    }
}
