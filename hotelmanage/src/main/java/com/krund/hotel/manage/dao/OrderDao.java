/**
*
* OrderDao.java
* Copyright(C) 克路德智能工程有限公司
* @date 2018-05-11
*/
package com.krund.hotel.manage.dao;

import com.krund.hotel.manage.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao extends BaseDao<Order> {
    List<Order> searchSelective(Order order);
    int fakeDelete(Integer id);
    /**
    * @Description: 1 : 预定中（未入住） 2: 已支付 payAmount != null 3:已入住
    * @Param: [status]
    * @return: java.util.List<com.krund.hotel.manage.entity.Order>
    * @Author: Zhang Ziming
    * @Date: 2018/6/12
    */
    List<Order> searchByWechat(@Param("status") Integer status, @Param("ordernos")String ordernos);

    Order getOrderByRoomId(Integer roomId);

    Long checkStatus(Order order);

    List<Order> searchSelectivePage(Order order);

    Long searchSelectivePageCount(Order order);
}