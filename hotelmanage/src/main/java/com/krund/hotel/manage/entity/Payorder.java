/**
*
* Payorder.java
* Copyright(C) 克路德智能工程有限公司
* @date 2018-05-11
*/
package com.krund.hotel.manage.entity;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class Payorder {
    /**
     * 主键
     */
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 管理id
     */
    @ApiModelProperty(value = "管理id")
    private Integer mId;

    /**
     * 酒店id
     */
    @ApiModelProperty(value = "酒店id")
    private Integer hotelId;

    /**
     * order订单id
     */
    @ApiModelProperty(value = "订单id",required = true)
    private Integer orderId;

    /**
     * 事件
     */
    @ApiModelProperty(value = "支付事件",required = true)
    private String payEvent;

    /**
     * 收款、付款金额
     */
    @ApiModelProperty(value = "支付金额",required = true)
    private BigDecimal payAmount;

    /**
     * 收款、付款方式
     */
    @ApiModelProperty(value = "支付类型",required = true)
    private String payType;

    /**
     * 其他支付信息
     */
    @ApiModelProperty(value = "支付信息")
    private String payInfo;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 
     */
    @ApiModelProperty(value = "创建时间")
    private Date dateCreate;

    @ApiModelProperty(value = "支付事件名称，用于前端显示")
    private String payEventName;

    @ApiModelProperty(value = "创建时间-开始，用于搜索")
    private String dateCreateStart;

    @ApiModelProperty(value = "创建时间-结束，用于搜索")
    private String dateCreateEnd;

    @ApiModelProperty(value = "当前页")
    private Integer pageNum;

    @ApiModelProperty(value = "页面大小")
    private Integer pageSize;

    //数据分析字段
    @ApiModelProperty(value = "时间坐标轴")
    private String dc; //时间坐标轴

    @ApiModelProperty(value = "收入")
    private BigDecimal income; //收入

    @ApiModelProperty(value = "支出")
    private BigDecimal payout; //支出

    @ApiModelProperty(value = "利润")
    private BigDecimal profit; //利润

    @ApiModelProperty(value = "订单号")
    private String orderno; //订单号

    /**
     *
     * @mbg.generated 2018-05-11
     */
    public Payorder(Integer id, Integer mId, Integer hotelId, Integer orderId, String payEvent, BigDecimal payAmount, String payType, String payInfo, String remark, Date dateCreate) {
        this.id = id;
        this.mId = mId;
        this.hotelId = hotelId;
        this.orderId = orderId;
        this.payEvent = payEvent;
        this.payAmount = payAmount;
        this.payType = payType;
        this.payInfo = payInfo;
        this.remark = remark;
        this.dateCreate = dateCreate;
    }

    /**
     *
     * @mbg.generated 2018-05-11
     */
    public Payorder() {
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
     * 管理id
     * @return m_id 管理id
     */
    public Integer getmId() {
        return mId;
    }

    /**
     * 管理id
     * @param mId 管理id
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
     * order订单id
     * @return order_id order订单id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * order订单id
     * @param orderId order订单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 事件
     * @return pay_event 事件
     */
    public String getPayEvent() {
        return payEvent;
    }

    /**
     * 事件
     * @param payEvent 事件
     */
    public void setPayEvent(String payEvent) {
        this.payEvent = payEvent == null ? null : payEvent.trim();
    }

    /**
     * 收款、付款金额
     * @return pay_amount 收款、付款金额
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * 收款、付款金额
     * @param payAmount 收款、付款金额
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * 收款、付款方式
     * @return pay_type 收款、付款方式
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 收款、付款方式
     * @param payType 收款、付款方式
     */
    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    /**
     * 其他支付信息
     * @return pay_info 其他支付信息
     */
    public String getPayInfo() {
        return payInfo;
    }

    /**
     * 其他支付信息
     * @param payInfo 其他支付信息
     */
    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo == null ? null : payInfo.trim();
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

    public String getPayEventName() {
        if (this.payEvent == null)
            return "";
        switch (this.payEvent) {
            case "checkIn":
                return "入住";
            case "extend":
                return "续住";
            case "checkOut":
                return "退房";
        }
        return "";
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

    public String getDc() {
        return dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getPayout() {
        return payout;
    }

    public void setPayout(BigDecimal payout) {
        this.payout = payout;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }
}