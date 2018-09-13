package com.krund.hotel.manage.service;

import com.krund.hotel.manage.dto.Result;
import com.krund.hotel.manage.entity.Hotel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface HotelService {
    Result<Object> insertOrUpdate(Hotel hotel, HttpServletResponse response) throws Exception;
    Result<Hotel> getById(Integer id) throws Exception;
    Result<List<Hotel>> getAllByMid(HttpServletRequest request) throws Exception;
    Hotel getByHidMid(Integer id, Integer mId);
}
