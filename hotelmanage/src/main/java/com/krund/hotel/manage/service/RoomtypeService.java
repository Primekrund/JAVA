package com.krund.hotel.manage.service;

import com.krund.hotel.manage.dto.Result;
import com.krund.hotel.manage.entity.Roomtype;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface RoomtypeService {
    Result<Object> insertOrUpdate(HttpServletRequest request, Roomtype roomtype, HttpServletResponse response) throws Exception;
    Result<List<Roomtype>> getAllByMid(Integer mId) throws Exception;
    Result<List<Roomtype>> getAllByHid(Integer hId) throws Exception;
    Result<Object> delete(Integer id) throws Exception;
    Result<Object> getAllByHidByDevice(HttpServletRequest request) throws Exception;
}
