/**
*
* ManagerDao.java
* Copyright(C) 克路德智能工程有限公司
* @date 2018-05-09
*/
package com.krund.hotel.manage.dao;

import com.krund.hotel.manage.entity.Manager;
import org.apache.ibatis.annotations.Param;

public interface ManagerDao extends BaseDao<Manager> {
    /**
    * @Description: 登录
    * @Param: [username, password]
    * @return: com.krund.hotel.manage.entity.Manager
    * @Author: Zhang Ziming
    * @Date: 2018/5/10
    */
    Manager login(@Param("username") String username, @Param("password") String password);

    /**
    * @Description: 微信登录
    * @Param: [openId]
    * @return: com.krund.hotel.manage.entity.Manager
    * @Author: Zhang Ziming
    * @Date: 2018/5/10
    */
    Manager getByOpenId(String openId);

    Manager getByToken(String token);
}