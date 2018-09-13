package com.krund.hotel.manage.service;

import com.krund.hotel.manage.dto.Result;
import com.krund.hotel.manage.entity.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface OrderService {
    Result<Object> insertOrUpdate(HttpServletRequest request, Order order, HttpServletResponse response) throws Exception;
    Result<Object> cancel(Integer id) throws Exception;
    Result<Order> getOrderById(Integer id) throws Exception;
    Result<Object> directCheckIn(HttpServletRequest request, Order order, HttpServletResponse response) throws Exception;
    Result<Object> checkIn(Integer id) throws Exception;
    Result<Object> checkOut(Integer id) throws Exception;
    Result<Object> checkOut(Integer tId, String roomno) throws Exception;
    Result<Object> checkOutByRoomId(Integer id) throws Exception;
    Result<Object> extend(String count, Order order, Integer tId, String roomno) throws Exception;
    Result<List<Order>> searchSelective(Order order) throws Exception;
    Result<List<Order>> searchByWechat(Integer status, String ordernos)throws Exception;
    Result<Order> getOrderByRoomno(Integer tId, String roomno) throws Exception;
    Result<Order> getOrderByRoomId(Integer id) throws Exception;
    /** 
    * @Description: 自助机查询预订单 
    * @Param: [order] 
    * @return: com.krund.hotel.manage.dto.Result<com.krund.hotel.manage.entity.Order>
    * @Author: Zhang Ziming
    * @Date: 2018/5/16 
    */ 
    Result<Order> getOrderByDevice(HttpServletRequest request, Order order) throws Exception;
    Result<Object> checkInByDevice(HttpServletRequest request, Integer id) throws Exception;
    Result<Object> checkStatus(HttpServletRequest request, Order order) throws Exception;
    Result<Map<String, Object>> searchSelectivePage(Order order) throws Exception;
}
