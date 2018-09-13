package com.krund.hotel.manage.service;

import com.krund.hotel.manage.entity.Token;

import java.util.Date;

public interface TokenService {
    void insertOrUpdate(Token token) throws Exception;
    boolean verifyToken(Integer id, String token, Date validity) throws Exception;
}
