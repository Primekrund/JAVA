package com.krund.hotel.manage.entity;

import java.util.Date;

public class Token {
    private Integer hotelId;

    private String token;

    private Date validity;

    private String ip;

    private Date createDate;

    public Token(Integer hotelId, String token, Date validity, String ip, Date createDate) {
        this.hotelId = hotelId;
        this.token = token;
        this.validity = validity;
        this.ip = ip;
        this.createDate = createDate;
    }

    public Token() {
        super();
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}