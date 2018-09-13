package com.krund.hotel.manage.controller;

import com.krund.hotel.manage.annotation.RequestLimit;
import com.krund.hotel.manage.dto.Result;
import com.krund.hotel.manage.entity.Order;
import com.krund.hotel.manage.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @program: ihotel
 * @description: 预订单/订单控制器
 * @author: Zhang Ziming
 * @create: 2018-05-11 13:54
 **/
@Controller
@RequestMapping("/api/")
@Api(value = "预订单/订单控制器",description  = "预订单/订单控制器")
public class OrderController {
    @Resource
    private OrderService orderService;

    @RequestLimit //请求频率限制
    @RequestMapping(value = "orders",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "创建一个Order对象")
    public Result<Object> add(HttpServletRequest request, @ModelAttribute Order order, HttpServletResponse response) throws Exception{
        return orderService.insertOrUpdate(request, order,response);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "orders/cancel/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "取消预订单")
    public Result<Object> cancel(HttpServletRequest request, @ApiParam(name = "id",value = "id",required = true) @PathVariable("id") Integer id, HttpServletResponse response) throws Exception{
        return orderService.cancel(id);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "orders/{id}",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获得一个订单")
    public Result<Order> get(HttpServletRequest request, @ApiParam(name = "id",value = "id",required = true) @PathVariable("id") Integer id, HttpServletResponse response) throws Exception{
        return orderService.getOrderById(id);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "orders",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据条件搜索Order对象")
    public Result<List<Order>> search(HttpServletRequest request, @ModelAttribute Order order, HttpServletResponse response) throws Exception{
        return orderService.searchSelective(order);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "orders/directcheckin",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "生成预订单并入住，预订单将状态将同时变成入住")
    public Result<Object> directCheckIn(HttpServletRequest request, @ModelAttribute Order order, HttpServletResponse response) throws Exception{
        return orderService.directCheckIn(request, order, response);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "orders/checkin/{id}",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "入住")
    public Result<Object> checkin(HttpServletRequest request, @ApiParam(name = "id",value = "id",required = true) @PathVariable("id") Integer id, HttpServletResponse response) throws Exception{
        return orderService.checkIn(id);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "orders/checkout/{tid}/{roomno}",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "退房")
    public Result<Object> checkOut(HttpServletRequest request, @ApiParam(name = "tid",value = "tid",required = true) @PathVariable("tid") Integer tid, @ApiParam(name = "roomno",value = "roomno",required = true) @PathVariable("roomno") String roomno,HttpServletResponse response) throws Exception{
        return orderService.checkOut(tid,roomno);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "orders/checkout/{roomid}",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "退房")
    public Result<Object> checkOutByRoomId(HttpServletRequest request, @PathVariable("roomid") Integer roomid,HttpServletResponse response) throws Exception{
        return orderService.checkOutByRoomId(roomid);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "orders/extend/{count}/{tid}/{roomno}",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "续房")
    public Result<Object> extend(HttpServletRequest request, @ApiParam(name = "count",value = "count",required = true) @PathVariable("count") String count,@ApiParam(name = "tid",value = "tid",required = true) @PathVariable("tid") Integer tid, @ApiParam(name = "roomno",value = "roomno",required = true) @PathVariable("roomno") String roomno,HttpServletResponse response) throws Exception{
        return orderService.extend(count,null,tid,roomno);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "orders/extend/{count}",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "续房")
    public Result<Object> extend(HttpServletRequest request, @ApiParam(name = "count",value = "count",required = true) @PathVariable("count") String count,@ApiParam(name = "order",value = "order",required = true) @ModelAttribute Order order, HttpServletResponse response) throws Exception{
        return orderService.extend(count,order,null,null);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "orders/info/{tid}/{roomno}",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据房型id和房间号获取订单（入住订单）详情")
    public Result<Order> getOrderByRoomno(HttpServletRequest request, @ApiParam(name = "tid",value = "tid",required = true) @PathVariable("tid") Integer tid, @ApiParam(name = "roomno",value = "roomno",required = true) @PathVariable("roomno") String roomno,HttpServletResponse response) throws Exception{
        return orderService.getOrderByRoomno(tid,roomno);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "orders/info/{roomid}",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据房型id和房间号获取订单（入住订单）详情")
    public Result<Order> getOrderByRoomId(HttpServletRequest request, @ApiParam(name = "roomid",value = "roomid",required = true) @PathVariable("roomid") Integer roomid,HttpServletResponse response) throws Exception{
        return orderService.getOrderByRoomId(roomid);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "orders/searchpage",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据条件搜索订单")
    public Result<Map<String, Object>> searchSelectivePage(HttpServletRequest request, @ApiParam(name = "order",value = "order",required = true) @ModelAttribute Order order, HttpServletResponse response) throws Exception{
        return orderService.searchSelectivePage(order);
    }

    //==============================================自助机小程序接口========================================

    /**
    * @Description: 微信小程序预订
    * @Param: [request, order, response]
    * @return: com.krund.hotel.manage.dto.Result<java.lang.Object>
    * @Author: Zhang Ziming
    * @Date: 2018/6/11
    */
    @RequestLimit //请求频率限制
    @RequestMapping(value = "wechat/orders",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "创建一个Order对象")
    public Result<Object> addByWechat(HttpServletRequest request, @ModelAttribute Order order, HttpServletResponse response) throws Exception{
        return orderService.insertOrUpdate(request, order,response);
    }


    /**
     * @Description: 微信小程序查询预订单
     * @Param: [phone]
     * @return: void
     * @Author: Zhang Ziming
     * @Date: 2018/5/16
     */
    @RequestLimit //请求频率限制
    @RequestMapping(value = "wechat/orders",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "小程序接口:查询预订单,可根据手机号,身份证号,订单号查询")
    public Result<Order> getOrderByWechat(HttpServletRequest request, @ModelAttribute Order order) throws Exception{
        return orderService.getOrderByDevice(request, order);
    }
    //==============================================自助机接口========================================

    /** 
    * @Description: 查询预订单 
    * @Param: [request, order] 
    * @return: com.krund.hotel.manage.dto.Result<com.krund.hotel.manage.entity.Order>
    * @Author: Zhang Ziming
    * @Date: 2018/6/26 
    */ 
    @RequestLimit //请求频率限制
    @RequestMapping(value = "device/orders",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "自助机接口:查询预订单,可根据手机号,身份证号,订单号查询")
    public Result<Order> getOrderByDevice(HttpServletRequest request, @ModelAttribute Order order) throws Exception{
        return orderService.getOrderByDevice(request, order);
    }

    /**
    * @Description:入住
    * @Param: [id, response]
    * @return: void
    * @Author: Zhang Ziming
    * @Date: 2018/5/17
    */
    @RequestLimit //请求频率限制
    @RequestMapping(value = "device/orders/checkin/{id}",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "自助机接口:入住")
    public Result<Object> checkinByDevice(HttpServletRequest request, @ApiParam(name = "id",value = "订单id",required = true) @PathVariable("id") Integer id, HttpServletResponse response) throws Exception{
        return orderService.checkIn(id);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "device/orders/directcheckin",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "生成预订单并入住，预订单将状态将同时变成入住")
    public Result<Object> directCheckInByDevice(HttpServletRequest request, @ModelAttribute Order order, HttpServletResponse response) throws Exception{
        String []params = (String[]) request.getAttribute("params");
        order.setHotelId(Integer.parseInt(params[1]));
        return orderService.directCheckIn(request, order, response);
    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "device/orders/checkstatus",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "自助机接口:检查用户是否可以入住，只需要传idcard参数")
    public Result<Order> checkStatus(HttpServletRequest request, @ModelAttribute Order order) throws Exception{
        return orderService.getOrderByDevice(request, order);
    }
}
