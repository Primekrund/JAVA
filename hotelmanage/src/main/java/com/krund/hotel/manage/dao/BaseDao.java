package com.krund.hotel.manage.dao;

/**
 * Dao公共继承接口
 * @author Zhang Ziming
 * @param <T>
 */
public interface BaseDao<T> {
    /**
    * @Description: 删除记录
    * @Param: [id]
    * @return: int
    * @Author: Zhang Ziming
    * @Date: 2018/4/12
    */
    int deleteByPrimaryKey(Integer id);

    /**
    * @Description: 插入记录
    * @Param: [record]
    * @return: int
    * @Author: Zhang Ziming
    * @Date: 2018/4/12
    */
    int insert(T record);

    /**
    * @Description: 插入属性不为null的记录
    * @Param: [record]
    * @return: int
    * @Author: Zhang Ziming
    * @Date: 2018/4/12
    */
    int insertSelective(T record);

    /**
    * @Description: 根据主键查询
    * @Param: [id]
    * @return: T
    * @Author: Zhang Ziming
    * @Date: 2018/4/12
    */
    T selectByPrimaryKey(Integer id);

    /**
    * @Description: 根据不为null的属性更新记录
    * @Param: [record]
    * @return: int
    * @Author: Zhang Ziming
    * @Date: 2018/4/12
    */
    int updateByPrimaryKeySelective(T record);

    /**
    * @Description: 根据主键更新记录
    * @Param: [record]
    * @return: int
    * @Author: Zhang Ziming
    * @Date: 2018/4/12
    */
    int updateByPrimaryKey(T record);
}
