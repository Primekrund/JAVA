package com.krund.hotel.manage.service;

import com.krund.hotel.manage.dto.Result;
import com.krund.hotel.manage.entity.Payorder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface PayorderService {
    Result<Object> insertOrUpdate(HttpServletRequest request, Payorder payorder, HttpServletResponse response) throws Exception;
    Result<Object> pay(HttpServletRequest request, Payorder payorder, HttpServletResponse response) throws Exception;
    Result<Map<String, Object>> searchSelectivePage(Payorder payorder) throws Exception;
    Result<List<Payorder>> analysis(Payorder payorder) throws Exception;
}
