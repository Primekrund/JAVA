/**
*
* Guest.java
* Copyright(C) 克路德智能工程有限公司
* @date 2018-05-10
*/
package com.krund.hotel.manage.entity;

import java.util.Date;

public class Guest {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 身份证
     */
    private String idcard;

    /**
     * 真实姓名
     */
    private String actualname;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 身份信息 json
     */
    private String info;

    /**
     * 创建时间
     */
    private Date dateCreate;

    /**
     *
     * @mbg.generated 2018-05-10
     */
    public Guest(Integer id, String idcard, String actualname, String phone, String info, Date dateCreate) {
        this.id = id;
        this.idcard = idcard;
        this.actualname = actualname;
        this.phone = phone;
        this.info = info;
        this.dateCreate = dateCreate;
    }

    /**
     *
     * @mbg.generated 2018-05-10
     */
    public Guest() {
        super();
    }

    /**
     * 主键
     * @return id 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 身份证
     * @return idcard 身份证
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * 身份证
     * @param idcard 身份证
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    /**
     * 真实姓名
     * @return actualname 真实姓名
     */
    public String getActualname() {
        return actualname;
    }

    /**
     * 真实姓名
     * @param actualname 真实姓名
     */
    public void setActualname(String actualname) {
        this.actualname = actualname == null ? null : actualname.trim();
    }

    /**
     * 手机号
     * @return phone 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 身份信息 json
     * @return info 身份信息 json
     */
    public String getInfo() {
        return info;
    }

    /**
     * 身份信息 json
     * @param info 身份信息 json
     */
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
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