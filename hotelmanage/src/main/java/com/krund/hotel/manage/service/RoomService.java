package com.krund.hotel.manage.service;

import com.krund.hotel.manage.dto.Result;
import com.krund.hotel.manage.entity.Room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface RoomService {
    Result<Object> insertOrUpdate(HttpServletRequest request, Room room, HttpServletResponse response) throws Exception;
    Result<Room> getById(Integer id) throws Exception;
    /** 
    * @Description: 根据manager_id和roomtype_id查询 
    * @Param: [mId, tId] 
    * @return: com.krund.hotel.manage.dto.Result<java.util.List<com.krund.hotel.manage.entity.Room>>
    * @Author: Zhang Ziming
    * @Date: 2018/5/11 
    */ 
    Result<List<Room>> getAll(HttpServletRequest request, Integer hId, Integer status, Integer tId, String roomno) throws Exception;

    Result<Object> delete(Integer id) throws Exception;
    
    /** 
    * @Description: 锁房 
    * @Param: [id, response] 
    * @return: void 
    * @Author: Zhang Ziming
    * @Date: 2018/5/11 
    */ 
    Result<Object> lockRoom(Integer id, Integer minute) throws Exception;

    Result<Object> unLockRoom(Integer id) throws Exception;

    /** 
    * @Description: 获得可用房间 
    * @Param: [tId] 
    * @return: com.krund.hotel.manage.dto.Result<java.util.List<com.krund.hotel.manage.entity.Room>>
    * @Author: Zhang Ziming
    * @Date: 2018/6/8 
    */ 
    Result<List<Room>> getUsableByTid(HttpServletRequest request, Integer tId) throws Exception;

    Result<Byte> getRoomStatus(HttpServletRequest request, Room room) throws Exception;
}
