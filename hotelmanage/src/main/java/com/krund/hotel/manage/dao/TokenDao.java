package com.krund.hotel.manage.dao;

import com.krund.hotel.manage.entity.Token;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface TokenDao extends BaseDao<Token> {
    /**
    * @Description: 验证token
    * @Param: [id, token, validity]
    * @return: boolean
    * @Author: Zhang Ziming
    * @Date: 2018/4/12
    */
    boolean verifyToken(@Param("id")Integer id, @Param("token")String token, @Param("validity")Date validity);
}