/**
*
* Order.java
* Copyright(C) 克路德智能工程有限公司
* @date 2018-05-11
*/
package com.krund.hotel.manage.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    /**
     * 主键
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
     * 订单单号
     */
    private String orderno;

    /**
     * 订单类别  1.预订单 2.订单
     */
    private Byte ordertype;

    /**
     * 1.普通 2.钟点房
     */
    private Byte livetype;

    /**
     * 房型
     */
    private Integer roomtypeId;

    /**
     * 房间id
     */
    private Integer roomId;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 来源
     */
    private String source;

    /**
     * 抵达时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateIn;

    /**
     * 离店时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateOut;

    /**
     * 顾客姓名
     */
    private String actualname;

    /**
     * 门卡卡号
     */
    private String iccard;

    /**
     * 顾客身份证
     */
    private String idcard;

    /**
     * 顾客电话
     */
    private String phone;

    /**
     * 收款金额
     */
    private BigDecimal payAmount;

    /**
     * 状态 1.未入住 2.入住 3.退房
     */
    private Byte status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date dateCreate;

    private String statusName;

    private String livetypeName;

    private String roomno; //房间号

    private String roomtypeName; //房型
    private String dateCreateStart; //创建时间开始
    private String dateCreateEnd; //创建时间结束
    private String criteria; //房间号 手机 姓名
    /**
     * 用于分页的参数，使用pagehelper默认的参数名
     */
    private Integer pageNum = 1;
    private Integer pageSize = 20;

    /**
     *
     * @mbg.generated 2018-05-11
     */
    public Order(Integer id, Integer mId, Integer hotelId, String orderno, Byte ordertype, Byte livetype, Integer roomtypeId, Integer roomId, String contacts, String source, Date dateIn, Date dateOut, String actualname, String iccard, String idcard, String phone, BigDecimal payAmount, Byte status, String remark, Date dateCreate) {
        this.id = id;
        this.mId = mId;
        this.hotelId = hotelId;
        this.orderno = orderno;
        this.ordertype = ordertype;
        this.livetype = livetype;
        this.roomtypeId = roomtypeId;
        this.roomId = roomId;
        this.contacts = contacts;
        this.source = source;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.actualname = actualname;
        this.iccard = iccard;
        this.idcard = idcard;
        this.phone = phone;
        this.payAmount = payAmount;
        this.status = status;
        this.remark = remark;
        this.dateCreate = dateCreate;
    }

    /**
     *
     * @mbg.generated 2018-05-11
     */
    public Order() {
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
     * 订单单号
     * @return orderno 订单单号
     */
    public String getOrderno() {
        return orderno;
    }

    /**
     * 订单单号
     * @param orderno 订单单号
     */
    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    /**
     * 订单类别  1.预订单 2.订单
     * @return ordertype 订单类别  1.预订单 2.订单
     */
    public Byte getOrdertype() {
        return ordertype;
    }

    /**
     * 订单类别  1.预订单 2.订单
     * @param ordertype 订单类别  1.预订单 2.订单
     */
    public void setOrdertype(Byte ordertype) {
        this.ordertype = ordertype;
    }

    /**
     * 1.普通 2.钟点房
     * @return livetype 1.普通 2.钟点房
     */
    public Byte getLivetype() {
        return livetype;
    }

    /**
     * 1.普通 2.钟点房
     * @param livetype 1.普通 2.钟点房
     */
    public void setLivetype(Byte livetype) {
        this.livetype = livetype;
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
     * 房间id
     * @return room_id 房间id
     */
    public Integer getRoomId() {
        return roomId;
    }

    /**
     * 房间id
     * @param roomId 房间id
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    /**
     * 联系人
     * @return contacts 联系人
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * 联系人
     * @param contacts 联系人
     */
    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    /**
     * 来源
     * @return source 来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 来源
     * @param source 来源
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * 抵达时间
     * @return date_in 抵达时间
     */
    public Date getDateIn() {
        return dateIn;
    }

    /**
     * 抵达时间
     * @param dateIn 抵达时间
     */
    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    /**
     * 离店时间
     * @return date_out 离店时间
     */
    public Date getDateOut() {
        return dateOut;
    }

    /**
     * 离店时间
     * @param dateOut 离店时间
     */
    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    /**
     * 顾客姓名
     * @return actualname 顾客姓名
     */
    public String getActualname() {
        return actualname;
    }

    /**
     * 顾客姓名
     * @param actualname 顾客姓名
     */
    public void setActualname(String actualname) {
        this.actualname = actualname == null ? null : actualname.trim();
    }

    /**
     * 门卡卡号
     * @return iccard 门卡卡号
     */
    public String getIccard() {
        return iccard;
    }

    /**
     * 门卡卡号
     * @param iccard 门卡卡号
     */
    public void setIccard(String iccard) {
        this.iccard = iccard == null ? null : iccard.trim();
    }

    /**
     * 顾客身份证
     * @return idcard 顾客身份证
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * 顾客身份证
     * @param idcard 顾客身份证
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    /**
     * 顾客电话
     * @return phone 顾客电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 顾客电话
     * @param phone 顾客电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 收款金额
     * @return pay_amount 收款金额
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * 收款金额
     * @param payAmount 收款金额
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * 状态 1.未入住 2.入住 3.退房
     * @return status 状态 1.未入住 2.入住 3.退房
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 状态 1.未入住 2.入住 3.退房
     * @param status 状态 1.未入住 2.入住 3.退房
     */
    public void setStatus(Byte status) {
        this.status = status;
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

    public String getStatusName() {
        switch (this.status.intValue()) {
            case 1:
                return "未入住";
            case 2:
                return "已入住";
            case 3:
                return "已退房";
        }
        return "";
    }

    public String getLivetypeName() {
        switch (this.livetype.intValue()) {
            case 1:
                return "全天房";
            case 2:
                return "钟点房";
            case 3:
                return "长包房";
        }
        return "";
    }

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }

    public String getRoomtypeName() {
        return roomtypeName;
    }

    public void setRoomtypeName(String roomtypeName) {
        this.roomtypeName = roomtypeName;
    }

    public String getDateCreateStart() {
        return dateCreateStart;
    }

    public void setDateCreateStart(String dateCreateStart) {
        this.dateCreateStart = dateCreateStart;
    }

    public String getDateCreateEnd() {
        return dateCreateEnd;
    }

    public void setDateCreateEnd(String dateCreateEnd) {
        this.dateCreateEnd = dateCreateEnd;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}