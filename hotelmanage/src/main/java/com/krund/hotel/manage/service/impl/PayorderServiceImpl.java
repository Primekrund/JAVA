package com.krund.hotel.manage.service.impl;

import com.krund.hotel.manage.dao.ManagerDao;
import com.krund.hotel.manage.dao.OrderDao;
import com.krund.hotel.manage.dao.PayorderDao;
import com.krund.hotel.manage.dao.RoomDao;
import com.krund.hotel.manage.dto.Result;
import com.krund.hotel.manage.entity.Manager;
import com.krund.hotel.manage.entity.Order;
import com.krund.hotel.manage.entity.Payorder;
import com.krund.hotel.manage.entity.Room;
import com.krund.hotel.manage.pay.wechat.WechatTrade;
import com.krund.hotel.manage.pay.wechat.entity.WechatUnifiedOrder;
import com.krund.hotel.manage.service.PayorderService;
import com.krund.hotel.manage.util.IpUtil;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: ihotel
 * @description: 账务业务逻辑
 * @author: Zhang Ziming
 * @create: 2018-05-14 10:54
 **/
@Service
public class PayorderServiceImpl implements PayorderService {
    @Resource
    private PayorderDao payorderDao;
    @Resource
    private OrderDao orderDao;
    @Resource
    private RoomDao roomDao;
    @Resource
    private ManagerDao managerDao;
    @Resource
    private WechatTrade wechatTrade;

    @Override
    public Result<Object> insertOrUpdate(HttpServletRequest request, Payorder payorder, HttpServletResponse response) throws Exception{
        boolean flag = false;
        Integer managerId = (Integer)request.getAttribute("managerId");
        payorder.setmId(managerId);
        if(payorder.getId() == null){
            if (payorder.getOrderId() == null || StringUtils.isEmpty(payorder.getPayEvent())){
                return Result.error("提交的账单缺少字段!");
            }
            //创建账单成功，执行支付成功后的操作
            Order order = orderDao.selectByPrimaryKey(payorder.getOrderId());
            if (order == null)
                return Result.error("未查询到订单信息!");
            Room room = roomDao.selectByPrimaryKey(order.getRoomId());
            if (room == null)
                return Result.error("房间不存在!");
            //入住
            if ("checkIn".equals(payorder.getPayEvent())){
                if (!(room.getStatus() == 1 || room.getStatus() == 10))
                    return Result.error("该房间不可入住!");
                //更新订单信息
                Order tmp = new Order();
                tmp.setId(order.getId());
                tmp.setPayAmount(payorder.getPayAmount());
                tmp.setStatus((byte)2);
                orderDao.updateByPrimaryKeySelective(tmp);
                //更新房态
                Room roomtmp = new Room();
                roomtmp.setId(room.getId());
                roomtmp.setStatus((byte)2);
                roomDao.updateByPrimaryKeySelective(roomtmp);
                flag = true;
            }
            //续房
            if("extend".equals(payorder.getPayEvent())){
                if (StringUtils.isEmpty(payorder.getPayInfo()))
                    return Result.error("账单中的续房天数参数丢失!");
                Order tmp = new Order();
                tmp.setPayAmount(payorder.getPayAmount().add(order.getPayAmount()));
                tmp.setId(order.getId());
                //结束时间增加
                Integer count = Integer.parseInt(payorder.getPayInfo());
                DateTime dateOut = new DateTime(order.getDateOut());
                if (order.getLivetype().intValue() != 2){
                    DateTime newDateOut = dateOut.plusDays(count);
                    tmp.setDateOut(newDateOut.toDate());
                }
                else {
                    DateTime newDateOut = dateOut.plusHours(count);
                    tmp.setDateOut(newDateOut.toDate());
                }
                orderDao.updateByPrimaryKeySelective(tmp);
                flag = true;
            }
            //退房
            if ("checkOut".equals(payorder.getPayEvent())){
                //更新订单信息
                Order tmp = new Order();
                tmp.setId(order.getId());
                tmp.setStatus((byte)3);
                orderDao.updateByPrimaryKeySelective(tmp);
                //更新房态
                Room roomtmp = new Room();
                roomtmp.setId(room.getId());
                roomtmp.setStatus((byte)3);
                roomDao.updateByPrimaryKeySelective(roomtmp);
                payorder.setPayAmount(payorder.getPayAmount().multiply(new BigDecimal(-1)));
                flag = true;
            }
            if (!flag){
                return Result.error("传入的支付事件错误，请传入checkIn、checkOut、extend！");
            }
            int i = payorderDao.insert(payorder);
            if (i == 0) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return Result.error("创建账单失败，操作已经回滚！");
            }
            return Result.success();
        }
        /*payorderDao.updateByPrimaryKeySelective(payorder);
        response.setStatus(201); //新建或修改成功*/
        return Result.error("账单信息创建后不允许修改!");
    }

    @Override
    public Result<Object> pay(HttpServletRequest request, Payorder payorder, HttpServletResponse response) throws Exception {
        Integer managerId = (Integer)request.getAttribute("managerId");
        Manager manager = managerDao.selectByPrimaryKey(managerId);
        Result<Object> result = this.insertOrUpdate(request, payorder, response);
        if (result.getRet() == 0)
            return result;
        //发起支付
        WechatUnifiedOrder wechatUnifiedOrder = new WechatUnifiedOrder();
        wechatUnifiedOrder.setBody(payorder.getPayInfo());
        //request.setDetail("一个好商品");
        //request.setGoods_tag("测试");
        wechatUnifiedOrder.setOut_trade_no(payorder.getRemark());
        wechatUnifiedOrder.setFee_type("CNY");
        wechatUnifiedOrder.setTotal_fee((payorder.getPayAmount().multiply(new BigDecimal(100)).intValue()));
        wechatUnifiedOrder.setSpbill_create_ip(IpUtil.getIpAddr(request));
        //request.setTime_start(System.currentTimeMillis() / 1000 +"");
        //request.setLimit_pay("no_credit");
        wechatUnifiedOrder.setProduct_id(payorder.getId().toString());
        wechatUnifiedOrder.setOpenid(manager.getOpenid());
        return Result.success(wechatTrade.unifiedOrderRequestForJSAPI(wechatUnifiedOrder));
    }

    @Override
    public Result<Map<String, Object>> searchSelectivePage(Payorder payorder) throws Exception {
        Long total = payorderDao.searchSelectivePageCount(payorder);
        Map<String, Object> map = new HashMap<>();
        map.put("pageNum", payorder.getPageNum());
        map.put("pageSize", payorder.getPageSize());
        map.put("total", total);
        if (payorder.getPageNum() <= 0)
            payorder.setPageNum(1);
        payorder.setPageNum((payorder.getPageNum() - 1) * payorder.getPageSize());
        List<Payorder> list = payorderDao.searchSelectivePage(payorder);
        map.put("list", list);
        return Result.success(map);
    }

    @Override
    public Result<List<Payorder>> analysis(Payorder payorder) throws Exception {
        if (StringUtils.isEmpty(payorder.getDateCreateStart()) || StringUtils.isEmpty(payorder.getDateCreateEnd())){
            DateTime end = new DateTime();
            DateTime start = end.minusDays(7);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String e = sdf.format(end.toDate());
            String s = sdf.format(start.toDate());
            payorder.setDateCreateEnd(e);
            payorder.setDateCreateStart(s);
        }
        List<Payorder> list = payorderDao.analysis(payorder);
        if (list.isEmpty())
            return Result.error("没有查询到数据！");
        return Result.success(list);
    }
}
