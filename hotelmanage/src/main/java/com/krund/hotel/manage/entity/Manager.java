/**
*
* Manager.java
* Copyright(C) 克路德智能工程有限公司
* @date 2018-05-10
*/
package com.krund.hotel.manage.entity;

import java.util.Date;

public class Manager {
    /**
     * 
     */
    private Integer id;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 登陆用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date dateCreate;

    /**
     *
     * @mbg.generated 2018-05-10
     */
    public Manager(Integer id, String openid, String username, String password, Date dateCreate) {
        this.id = id;
        this.openid = openid;
        this.username = username;
        this.password = password;
        this.dateCreate = dateCreate;
    }

    /**
     *
     * @mbg.generated 2018-05-10
     */
    public Manager() {
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
     * 微信openid
     * @return openid 微信openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 微信openid
     * @param openid 微信openid
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    /**
     * 登陆用户名
     * @return username 登陆用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 登陆用户名
     * @param username 登陆用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 密码
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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