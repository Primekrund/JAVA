package com.krund.hotel.manage.service.impl;

import com.krund.hotel.manage.dao.HotelDao;
import com.krund.hotel.manage.dto.Result;
import com.krund.hotel.manage.entity.Hotel;
import com.krund.hotel.manage.service.HotelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @program: ihotel
 * @description: 酒店业务逻辑
 * @author: Zhang Ziming
 * @create: 2018-05-10 14:37
 **/
@Service
public class HotelServiceImpl implements HotelService {
    @Resource
    private HotelDao hotelDao;

    @Override
    public Result<Object> insertOrUpdate(Hotel hotel, HttpServletResponse response) throws Exception{
        if(hotel.getId() == null){
            hotelDao.insert(hotel);
            response.setStatus(201); //新建或修改成功
            return Result.success();
        }
        hotelDao.updateByPrimaryKeySelective(hotel);
        response.setStatus(201); //新建或修改成功
        return Result.success();
    }

    @Override
    public Result<Hotel> getById(Integer id) throws Exception{
        return Result.success(hotelDao.selectByPrimaryKey(id));
    }

    @Override
    public Result<List<Hotel>> getAllByMid(HttpServletRequest request) throws Exception{
        Integer mId = (Integer) request.getAttribute("managerId");
        return Result.success(hotelDao.getAllByMid(mId));
    }

    @Override
    public Hotel getByHidMid(Integer id, Integer mId) {
        return hotelDao.getByHidMid(id, mId);
    }
}
