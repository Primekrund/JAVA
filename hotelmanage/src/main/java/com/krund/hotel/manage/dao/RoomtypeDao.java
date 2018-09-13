/**
*
* RoomtypeDao.java
* Copyright(C) 克路德智能工程有限公司
* @date 2018-05-09
*/
package com.krund.hotel.manage.dao;

import com.krund.hotel.manage.entity.Roomtype;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomtypeDao extends BaseDao<Roomtype> {
    List<Roomtype> getAllByMid(Integer mId);

    List<Roomtype> getAllByHid(Integer hId);

    Long getCountByHidAndName(@Param("hId")Integer hId, @Param("name")String name);

    Long getUsingCount(Integer id);

    Long getCountByHidTid(@Param("hId")Integer hId, @Param("tId")Integer tId);
}