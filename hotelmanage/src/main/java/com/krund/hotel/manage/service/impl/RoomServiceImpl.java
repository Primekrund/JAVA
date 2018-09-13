package com.krund.hotel.manage.service.impl;

import com.krund.hotel.manage.dao.HotelDao;
import com.krund.hotel.manage.dao.RoomDao;
import com.krund.hotel.manage.dao.RoomtypeDao;
import com.krund.hotel.manage.dto.Result;
import com.krund.hotel.manage.entity.Hotel;
import com.krund.hotel.manage.entity.Room;
import com.krund.hotel.manage.service.RoomService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @program: ihotel
 * @description: 房间业务逻辑
 * @author: Zhang Ziming
 * @create: 2018-05-11 11:02
 **/
@Service
public class RoomServiceImpl implements RoomService {
    @Resource
    private RoomDao roomDao;
    @Resource
    private HotelDao hotelDao;
    @Resource
    private RoomtypeDao roomtypeDao;

    @Override
    public Result<Object> insertOrUpdate(HttpServletRequest request, Room room, HttpServletResponse response) throws Exception{
        Integer managerId = (Integer)request.getAttribute("managerId");
        room.setmId(managerId);
        if(room.getId() == null){
            //验证是否可以添加
            Long c = roomDao.getCountByHidAndRoomno(room.getHotelId(), room.getRoomno());
            if (c > 0)
                return Result.error("在该酒店下已有相同的房间号!");
            if (room.getFloor() == null)
                return Result.error("楼层为空");
            if (room.getHotelId() == null)
                return Result.error("酒店id为空");
            if (room.getLockno() == null)
                return Result.error("锁号为空");
            if (room.getRoomno() == null)
                return Result.error("房间号为空");
            if (room.getRoomtypeId() == null)
                return Result.error("房型id为空");
            room.setStatus((byte) 1);
            roomDao.insert(room);
            response.setStatus(201); //新建或修改成功
            return Result.success();
        }
        roomDao.updateByPrimaryKeySelective(room);
        response.setStatus(201); //新建或修改成功
        return Result.success();
    }

    @Override
    public Result<Room> getById(Integer id) throws Exception{
        Room room = roomDao.selectByPrimaryKey(id);
        Date date = new Date();
        if (room.getStatus() != null && room.getDateLock() != null){
            if (room.getStatus().intValue() == 10 && room.getDateLock().before(date))
                room.setStatus((byte) 1);
        }
        return Result.success(room);
    }

    @Override
    public Result<List<Room>> getAll(HttpServletRequest request, Integer hId,Integer status, Integer tId, String roomno) throws Exception{
        Integer managerId = (Integer)request.getAttribute("managerId");
        Hotel hotel = hotelDao.getByHidMid(hId,managerId);
        if (hotel == null)
            return Result.error("当前用户不具备管理该酒店的权限!");
        List<Room> rooms = roomDao.getAll(hId, status, tId, roomno);
        Date date = new Date();
        for (Room room:
             rooms) {
            if (room.getStatus() == null || room.getDateLock() == null)
                continue;
            if (room.getStatus().intValue() == 10 && room.getDateLock().before(date))
                room.setStatus((byte) 1);
        }
        return Result.success(rooms);
    }

    @Override
    public Result<Object> delete(Integer id) throws Exception {
        Room room = roomDao.selectByPrimaryKey(id);
        if (room == null)
            return Result.error("没有这个房间!");
        if (room.getStatus() == 2)
            return Result.error("房间正在被使用,无法删除!");
        return Result.success(roomDao.deleteByPrimaryKey(id));
    }

    @Override
    public Result<Object> lockRoom(Integer id, Integer minute) throws Exception{
        DateTime date = new DateTime();
        date.plusMinutes(minute);
        int i = roomDao.lockRoom(id, date.toDate());
        if (i > 0)
            return Result.success();
        return Result.error("锁定房间失败,受影响行数为0");
    }

    @Override
    public Result<Object> unLockRoom(Integer id) throws Exception {
        int i = roomDao.unLockRoom(id);
        if (i > 0)
            return Result.success();
        return Result.error("解锁房间失败,受影响行数为0");
    }

    @Override
    public Result<List<Room>> getUsableByTid(HttpServletRequest request, Integer tId) throws Exception{
        String []params = (String[]) request.getAttribute("params");
        Long c = roomtypeDao.getCountByHidTid(Integer.parseInt(params[1]), tId);
        if (c == 0)
            return Result.error("该酒店下没有这个房型数据");
        return Result.success(roomDao.getUsableByTid(tId));
    }

    @Override
    public Result<Byte> getRoomStatus(HttpServletRequest request, Room room) throws Exception {
        String []params = (String[]) request.getAttribute("params");
        room.setmId(Integer.parseInt(params[0]));
        room.setHotelId(Integer.parseInt(params[1]));
        Room room1 = roomDao.getRoomStatus(room);
        if (room1 == null)
            return Result.error("没有查询到房间");
        Date date = new Date();
        if (room1.getStatus() != null && room1.getDateLock() != null){
            if (room1.getStatus().intValue() == 10 && room1.getDateLock().before(date))
                room1.setStatus((byte) 1);
        }
        return Result.success(room1.getStatus());
    }
}
