package com.krund.hotel.manage.service.impl;

import com.krund.hotel.manage.dao.HotelDao;
import com.krund.hotel.manage.dao.RoomDao;
import com.krund.hotel.manage.dao.RoomtypeDao;
import com.krund.hotel.manage.dto.Result;
import com.krund.hotel.manage.entity.Roomtype;
import com.krund.hotel.manage.service.RoomtypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @program: ihotel
 * @description: 房型业务逻辑
 * @author: Zhang Ziming
 * @create: 2018-05-11 09:52
 **/
@Service
public class RoomtypeServiceImpl implements RoomtypeService {
    @Resource
    private RoomtypeDao roomtypeDao;
    @Resource
    private RoomDao roomDao;
    @Resource
    private HotelDao hotelDao;

    @Override
    public Result<Object> insertOrUpdate(HttpServletRequest request,Roomtype roomtype, HttpServletResponse response) throws Exception{
        Integer managerId = (Integer)request.getAttribute("managerId");
        roomtype.setmId(managerId);
        if(roomtype.getId() == null){
            Long c = roomtypeDao.getCountByHidAndName(roomtype.getHotelId(), roomtype.getName());
            if (c > 0)
                return Result.error("在该酒店下已有相同的房型名称!");
            if (roomtype.getHotelId() == null)
                return Result.error("酒店id为空");
            if (roomtype.getDeposit() == null)
                return Result.error("押金为空");
            if (roomtype.getHourPrice() == null)
                return Result.error("钟点房价格为空");
            if (roomtype.getLongtermPrice() == null)
                return Result.error("长租房价格为空");
            if (roomtype.getPrice() == null)
                return Result.error("价格为空");
            if (roomtype.getName() == null)
                return Result.error("房型名称为空");
            roomtypeDao.insert(roomtype);
            response.setStatus(201); //新建或修改成功
            return Result.success();
        }
        roomtypeDao.updateByPrimaryKeySelective(roomtype);
        response.setStatus(201); //新建或修改成功
        return Result.success();
    }

    @Override
    public Result<List<Roomtype>> getAllByMid(Integer mId) throws Exception{
        return Result.success(roomtypeDao.getAllByMid(mId));
    }

    @Override
    public Result<List<Roomtype>> getAllByHid(Integer hId) throws Exception{
        return Result.success(roomtypeDao.getAllByHid(hId));
    }

    @Override
    public Result<Object> delete(Integer id) throws Exception {
        Roomtype roomtype = roomtypeDao.selectByPrimaryKey(id);
        if (roomtype == null)
            return Result.error("该房型不存在!");
        Long c = roomtypeDao.getUsingCount(id);
        if (c > 0)
            return Result.error("该房型正在被使用,无法删除!");
        return Result.success(roomtypeDao.deleteByPrimaryKey(id));
    }

    @Override
    public Result<Object> getAllByHidByDevice(HttpServletRequest request) throws Exception {
        String []params = (String[])request.getAttribute("params");
        List<Roomtype> roomtypes = roomtypeDao.getAllByHid(Integer.parseInt(params[1]));
        Integer c;
        for (Roomtype roomtype:
             roomtypes) {
            c = roomDao.getUsableCountByTid(roomtype.getId());
            roomtype.setUsableCount(c);
        }
        return Result.success(roomtypes);
    }
}
