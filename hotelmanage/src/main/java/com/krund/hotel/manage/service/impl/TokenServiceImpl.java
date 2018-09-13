package com.krund.hotel.manage.service.impl;

import com.krund.hotel.manage.dao.TokenDao;
import com.krund.hotel.manage.entity.Token;
import com.krund.hotel.manage.service.TokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {
    @Resource
    private TokenDao tokenDao;
    @Override
    public void insertOrUpdate(Token token) throws Exception{
        Token t = tokenDao.selectByPrimaryKey(token.getHotelId());
        if(t!=null){
            tokenDao.updateByPrimaryKeySelective(token);
            return;
        }
        tokenDao.insert(token);
    }

    @Override
    public boolean verifyToken(Integer id, String token, Date validity) throws Exception{
        return tokenDao.verifyToken(id,token,validity);
    }
}
