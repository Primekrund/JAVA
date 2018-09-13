/**
*
* Roomtype.java
* Copyright(C) 克路德智能工程有限公司
* @date 2018-05-10
*/
package com.krund.hotel.manage.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Roomtype {
    /**
     * 
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer mId;

    /**
     * 酒店id
     */
    private Integer hotelId;

    /**
     * 房间名称
     */
    private String name;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 长租房单价
     */
    private BigDecimal longtermPrice;

    /**
     * 钟点房单价
     */
    private BigDecimal hourPrice;

    /**
     * 押金
     */
    private BigDecimal deposit;

    /**
     * 
     */
    private Date dateCreate;

    private Integer usableCount;

    /**
     *
     * @mbg.generated 2018-05-10
     */
    public Roomtype(Integer id, Integer mId, Integer hotelId, String name, BigDecimal price, BigDecimal longtermPrice, BigDecimal hourPrice, BigDecimal deposit, Date dateCreate) {
        this.id = id;
        this.mId = mId;
        this.hotelId = hotelId;
        this.name = name;
        this.price = price;
        this.longtermPrice = longtermPrice;
        this.hourPrice = hourPrice;
        this.deposit = deposit;
        this.dateCreate = dateCreate;
    }

    /**
     *
     * @mbg.generated 2018-05-10
     */
    public Roomtype() {
        super();
    }

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 用户id
     * @return m_id 用户id
     */
    public Integer getmId() {
        return mId;
    }

    /**
     * 用户id
     * @param mId 用户id
     */
    public void setmId(Integer mId) {
        this.mId = mId;
    }

    /**
     * 酒店id
     * @return hotel_id 酒店id
     */
    public Integer getHotelId() {
        return hotelId;
    }

    /**
     * 酒店id
     * @param hotelId 酒店id
     */
    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    /**
     * 房间名称
     * @return name 房间名称
     */
    public String getName() {
        return name;
    }

    /**
     * 房间名称
     * @param name 房间名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 单价
     * @return price 单价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 单价
     * @param price 单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 钟点房单价
     * @return longtermPrice 长租房单价
     */
    public BigDecimal getLongtermPrice() {
        return longtermPrice;
    }

    /**
     * 单价
     * @param longtermPrice 长租房单价
     */
    public void setLongtermPrice(BigDecimal longtermPrice) {
        this.longtermPrice = longtermPrice;
    }

    /**
     * 钟点房单价
     * @return hourPrice 钟点房单价
     */
    public BigDecimal getHourPrice() {
        return hourPrice;
    }

    /**
     * 钟点房单价
     * @param hourPrice 钟点房单价
     */
    public void setHourPrice(BigDecimal hourPrice) {
        this.hourPrice = hourPrice;
    }

    /**
     * 押金
     * @return deposit 押金
     */
    public BigDecimal getDeposit() {
        return deposit;
    }

    /**
     * 押金
     * @param deposit 押金
     */
    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    /**
     * 
     * @return date_create 
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     * 
     * @param dateCreate 
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getUsableCount() {
        return usableCount;
    }

    public void setUsableCount(Integer usableCount) {
        this.usableCount = usableCount;
    }
}