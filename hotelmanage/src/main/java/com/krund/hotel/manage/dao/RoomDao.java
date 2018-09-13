/**
*
* RoomDao.java
* Copyright(C) 克路德智能工程有限公司
* @date 2018-05-09
*/
package com.krund.hotel.manage.dao;

import com.krund.hotel.manage.entity.Room;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface RoomDao extends BaseDao<Room> {
    List<Room> getAll(@Param("hId") Integer hId,@Param("status")Integer status, @Param("tId") Integer tId, @Param("roomno") String roomno);
    int lockRoom(@Param("id") Integer id, @Param("date")Date date);

    int unLockRoom(Integer id);

    Long getCountByHidAndRoomno(@Param("hId")Integer hId, @Param("roomno")String roomno);

    List<Room> getUsableByTid(Integer tId);

    Integer getUsableCountByTid(Integer tId);

    Room getRoomStatus(Room room);
}