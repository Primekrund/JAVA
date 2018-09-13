/**
*
* HotelDao.java
* Copyright(C) 克路德智能工程有限公司
* @date 2018-05-09
*/
package com.krund.hotel.manage.dao;

import com.krund.hotel.manage.entity.Hotel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotelDao extends BaseDao<Hotel> {
    List<Hotel> getAllByMid(Integer mId);
    Hotel getByHidMid(@Param("id")Integer id, @Param("mId")Integer mId);
}