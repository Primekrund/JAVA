/**
*
* PayorderDao.java
* Copyright(C) 克路德智能工程有限公司
* @date 2018-05-11
*/
package com.krund.hotel.manage.dao;

import com.krund.hotel.manage.entity.Payorder;

import java.util.List;

public interface PayorderDao extends BaseDao<Payorder> {
    List<Payorder> searchSelectivePage(Payorder payorder);

    Long searchSelectivePageCount(Payorder payorder);

    List<Payorder> analysis(Payorder payorder);
}