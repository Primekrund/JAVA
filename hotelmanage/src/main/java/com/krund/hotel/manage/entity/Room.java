/**
*
* Room.java
* Copyright(C) 克路德智能工程有限公司
* @date 2018-05-10
*/
package com.krund.hotel.manage.entity;

import java.util.Date;

public class Room {
    /**
     * 
     */
    private Integer id;

    /**
     * 管理者id
     */
    private Integer mId;

    /**
     * 酒店id
     */
    private Integer hotelId;

    /**
     * 房型
     */
    private Integer roomtypeId;

    /**
     * 楼层
     */
    private Byte floor;

    /**
     * 房号
     */
    private String roomno;

    /**
     * 锁号
     */
    private String lockno;

    /**
     * 状态 1.净房 2.在住 3.脏房 4.维修 10.锁房 
     */
    private Byte status;

    /**
     * 锁房时间
     */
    private Date dateLock;

    /**
     * 创建时间
     */
    private Date dateCreate;
    
    private String roomtypeName;

    private String statusName;

    /**
     *
     * @mbg.generated 2018-05-10
     */
    public Room(Integer id, Integer mId, Integer hotelId, Integer roomtypeId, Byte floor, String roomno, String lockno, Byte status, Date dateLock, Date dateCreate) {
        this.id = id;
        this.mId = mId;
        this.hotelId = hotelId;
        this.roomtypeId = roomtypeId;
        this.floor = floor;
        this.roomno = roomno;
        this.lockno = lockno;
        this.status = status;
        this.dateLock = dateLock;
        this.dateCreate = dateCreate;
    }

    /**
     *
     * @mbg.generated 2018-05-10
     */
    public Room() {
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
     * 房型
     * @return roomtype_id 房型
     */
    public Integer getRoomtypeId() {
        return roomtypeId;
    }

    /**
     * 房型
     * @param roomtypeId 房型
     */
    public void setRoomtypeId(Integer roomtypeId) {
        this.roomtypeId = roomtypeId;
    }

    /**
     * 楼层
     * @return floor 楼层
     */
    public Byte getFloor() {
        return floor;
    }

    /**
     * 楼层
     * @param floor 楼层
     */
    public void setFloor(Byte floor) {
        this.floor = floor;
    }

    /**
     * 房号
     * @return roomno 房号
     */
    public String getRoomno() {
        return roomno;
    }

    /**
     * 房号
     * @param roomno 房号
     */
    public void setRoomno(String roomno) {
        this.roomno = roomno == null ? null : roomno.trim();
    }

    /**
     * 锁号
     * @return lockno 锁号
     */
    public String getLockno() {
        return lockno;
    }

    /**
     * 锁号
     * @param lockno 锁号
     */
    public void setLockno(String lockno) {
        this.lockno = lockno == null ? null : lockno.trim();
    }

    /**
     * 状态 1.净房 2.在住 3.脏房 4.维修 10.锁房 
     * @return status 状态 1.净房 2.在住 3.脏房 4.维修 10.锁房 
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 状态 1.净房 2.在住 3.脏房 4.维修 10.锁房 
     * @param status 状态 1.净房 2.在住 3.脏房 4.维修 10.锁房 
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 锁房时间
     * @return date_lock 锁房时间
     */
    public Date getDateLock() {
        return dateLock;
    }

    /**
     * 锁房时间
     * @param dateLock 锁房时间
     */
    public void setDateLock(Date dateLock) {
        this.dateLock = dateLock;
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

    public String getRoomtypeName() {
        return roomtypeName;
    }

    public void setRoomtypeName(String roomtypeName) {
        this.roomtypeName = roomtypeName;
    }

    public String getStatusName() {
        switch (this.status.intValue()) {
            case 1:
                return "净房";
            case 2:
                return "在住";
            case 3:
                return "脏房";
            case 4:
                return "维修房";
            case 10:
                return "锁房";
        }
        return "";
    }
}