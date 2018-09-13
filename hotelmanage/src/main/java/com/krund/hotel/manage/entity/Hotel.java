/**
*
* Hotel.java
* Copyright(C) 克路德智能工程有限公司
* @date 2018-05-10
*/
package com.krund.hotel.manage.entity;

import java.util.Date;

public class Hotel {
    /**
     * 
     */
    private Integer id;

    /**
     * 管理者id
     */
    private Integer mId;

    /**
     * 酒店名称
     */
    private String name;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 地址
     */
    private String address;

    /**
     * 电话
     */
    private String tel;

    /**
     * 接口密钥
     */
    private String apikey;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date dateCreate;

    /**
     *
     * @mbg.generated 2018-05-10
     */
    public Hotel(Integer id, Integer mId, String name, String city, String address, String tel, String apikey, String remark, Date dateCreate) {
        this.id = id;
        this.mId = mId;
        this.name = name;
        this.city = city;
        this.address = address;
        this.tel = tel;
        this.apikey = apikey;
        this.remark = remark;
        this.dateCreate = dateCreate;
    }

    /**
     *
     * @mbg.generated 2018-05-10
     */
    public Hotel() {
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
     * 管理者id
     * @return m_id 管理者id
     */
    public Integer getmId() {
        return mId;
    }

    /**
     * 管理者id
     * @param mId 管理者id
     */
    public void setmId(Integer mId) {
        this.mId = mId;
    }

    /**
     * 酒店名称
     * @return name 酒店名称
     */
    public String getName() {
        return name;
    }

    /**
     * 酒店名称
     * @param name 酒店名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 所在城市
     * @return city 所在城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 所在城市
     * @param city 所在城市
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 地址
     * @return address 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 地址
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 电话
     * @return tel 电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 电话
     * @param tel 电话
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * 接口密钥
     * @return apikey 接口密钥
     */
    public String getApikey() {
        return apikey;
    }

    /**
     * 接口密钥
     * @param apikey 接口密钥
     */
    public void setApikey(String apikey) {
        this.apikey = apikey == null ? null : apikey.trim();
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建时间
     * @return date_create 创建时间
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     * 创建时间
     * @param dateCreate 创建时间
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
}