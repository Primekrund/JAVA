package com.krund.hotel.manage.service.impl;

import com.krund.hotel.manage.dao.HotelDao;
import com.krund.hotel.manage.dao.OrderDao;
import com.krund.hotel.manage.dao.RoomDao;
import com.krund.hotel.manage.dao.RoomtypeDao;
import com.krund.hotel.manage.dto.Result;
import com.krund.hotel.manage.entity.Order;
import com.krund.hotel.manage.entity.Payorder;
import com.krund.hotel.manage.entity.Room;
import com.krund.hotel.manage.entity.Roomtype;
import com.krund.hotel.manage.service.OrderService;
import org.joda.time.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * @program: ihotel
 * @description: 预订单/订单业务逻辑
 * @author: Zhang Ziming
 * @create: 2018-05-11 13:53
 **/
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private RoomDao roomDao;
    @Resource
    private RoomtypeDao roomtypeDao;
    @Resource
    private HotelDao hotelDao;
    @Resource
    private RedisTemplate<String, Long> redisTemplate;

    @Override
    public Result insertOrUpdate(HttpServletRequest request, Order order, HttpServletResponse response) throws Exception{
        Integer managerId = (Integer)request.getAttribute("managerId");
        order.setmId(managerId);
        order.setStatus((byte) 1);
        Room room = roomDao.selectByPrimaryKey(order.getRoomId());
        if (room == null)
            return Result.error("roomId不正确");
        if (room.getRoomtypeId() != order.getRoomtypeId())
            return Result.error("roomtypeId不正确");
        if (room.getStatus().intValue() == 10 && room.getDateLock().after(new Date()))
            return Result.error("房间被占用");
        if(order.getId() == null){
            String phonePattern = "0?(13|14|15|17|18|19)[0-9]{9}";
            String idCardPattern = "\\d{17}[\\d|x]|\\d{15}";
            if (order.getOrdertype() == null)
                return Result.error("订单类型为空");
            if (order.getLivetype() == null)
                return Result.error("livetype为空");
            if (order.getActualname() == null)
                return Result.error("顾客姓名为空");
            if (order.getContacts() == null)
                return Result.error("联系人为空");
            if (order.getHotelId() == null)
                return Result.error("hotelId为空");
            if (order.getDateIn() == null)
                return Result.error("入住时间为空");
            if (order.getDateOut() == null)
                return Result.error("离开时间为空");
            if (order.getPhone() == null)
                return Result.error("手机号为空");
            if (order.getPhone().length() != 11)
                return Result.error("手机号长度不正确");
            if (!Pattern.matches(phonePattern,order.getPhone()))
                return Result.error("手机号格式不正确");
            if (order.getIdcard() == null)
                return Result.error("身份证号为空");
            if (order.getIdcard().length() != 18)
                return Result.error("身份证号长度不正确");
            if (!Pattern.matches(idCardPattern,order.getIdcard()))
                return Result.error("身份证号格式不正确");
            order.setOrderno(getOrderNo()); //获取订单号
            orderDao.insert(order);
            response.setStatus(201); //新建或修改成功
            //创建订单时锁房
            if (order.getOrdertype() == null)
                return Result.error("缺少ordertype参数");
            roomDao.lockRoom(order.getRoomId(), order.getDateOut());
            return Result.success(order.getOrderno());
        }
        orderDao.updateByPrimaryKeySelective(order);
        response.setStatus(201); //新建或修改成功
        return Result.success(order.getOrderno());
    }

    @Override
    public Result<Object> cancel(Integer id) throws Exception {
        Order order = orderDao.selectByPrimaryKey(id);
        if (order == null)
            return Result.error("订单不存在！");
        int i = orderDao.fakeDelete(id);
        int j = roomDao.unLockRoom(order.getRoomId());
        if (i == 0 || j == 0){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error("取消订单失败，有一个或者多个操作未成功！");
        }
        return Result.success();
    }

    @Override
    public Result<Order> getOrderById(Integer id) throws Exception {
        Order order = orderDao.selectByPrimaryKey(id);
        if (order == null)
            return Result.error("订单不存在！");
        return Result.success(order);
    }

    /** 
    * @Description: 利用redis自增生成订单号
    * @Param: [] 
    * @return: java.lang.String 
    * @Author: Zhang Ziming
    * @Date: 2018/6/8 
    */ 
    private String getOrderNo() throws Exception{
        //获取当前与当天结束时间的差值
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);
        DateTime dateEnd = new DateTime(calendar.getTimeInMillis());
        DateTime dateStart = new DateTime();
        Period p = new Period(dateStart, dateEnd, PeriodType.millis());
        //生成Key
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = simpleDateFormat.format(new Date());//下单时间，精确到秒
        String key =date.substring(0,8);//每天的日期，作为key存入redis,value为订单量
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();
        if (null == increment || increment.longValue() == 0) {//初始设置过期时间
            entityIdCounter.expire(p.getMillis(), TimeUnit.MILLISECONDS);
        }
        increment += 10000000;//前面补充0,转String类型后用substring(1)把1去掉
        String result = date + String.valueOf(increment).substring(1);
        return result;
    }
    
    @Override
    public Result<Object> directCheckIn(HttpServletRequest request, Order order, HttpServletResponse response) throws Exception{

        Result<Object> result = this.insertOrUpdate(request, order, response);
        if (result.getRet() == 0)
            return result;
        return checkIn(order.getId());
    }

    /** 
    * @Description: 入住,计算并返回账单信息
    * @Param: [id] 
    * @return: com.krund.hotel.manage.dto.Result<java.lang.Object>
    * @Author: Zhang Ziming
    * @Date: 2018/6/5 
    */ 
    @Override
    public Result<Object> checkIn(Integer id) throws Exception{
        //根据订单id获得订单
        Order order = orderDao.selectByPrimaryKey(id);
        if (order == null)
            return Result.error("未查询到订单信息!");
        Room room = roomDao.selectByPrimaryKey(order.getRoomId());
        if (room == null)
            return Result.error("房间不存在!");
        if (!(room.getStatus() == 1 || room.getStatus() == 10))
            return Result.error("该房间不可入住!");
        //检查支付状态，已经支付的订单直接入住，否则在支付成功确认账单后才能入住
        if (order.getPayAmount() != null){
            //更新订单信息
            Order tmp = new Order();
            tmp.setId(order.getId());
            tmp.setStatus((byte)2);
            orderDao.updateByPrimaryKeySelective(tmp);
            //更新房态
            Room roomtmp = new Room();
            roomtmp.setId(room.getId());
            roomtmp.setStatus((byte)2);
            roomDao.updateByPrimaryKeySelective(roomtmp);
            return Result.success("入住成功!预订单已经支付!");
        }
        //生成账单信息
        DateTime dateIn = new DateTime(order.getDateIn());
        DateTime dateOut = new DateTime(order.getDateOut());
        Roomtype roomtype = roomtypeDao.selectByPrimaryKey(room.getRoomtypeId());
        if (roomtype.getDeposit() == null)
            roomtype.setDeposit(new BigDecimal(0));
        Payorder payorder = new Payorder();
        payorder.setHotelId(order.getHotelId());
        payorder.setOrderId(order.getId());
        payorder.setPayEvent("checkIn"); //设置支付事件，提交支付订单后更新房态以及入住状态
        if (order.getLivetype().intValue() == 1){ //全天房，获得天数
            int days = Days.daysBetween(dateIn.withTimeAtStartOfDay(),dateOut.withTimeAtStartOfDay()).getDays();
            BigDecimal money = roomtype.getPrice().multiply(new BigDecimal(days)).add(roomtype.getDeposit());
            payorder.setPayAmount(money);
        }
        if (order.getLivetype().intValue() == 2){ //钟点房，获得小时数
            int hours = Hours.hoursBetween(dateIn,dateOut).getHours();
            int minutes = Minutes.minutesBetween(dateIn,dateOut).getMinutes() % 60;
            if (minutes > 0)
                hours += 1;
            BigDecimal money = roomtype.getHourPrice().multiply(new BigDecimal(hours)).add(roomtype.getDeposit());
            payorder.setPayAmount(money);
        }
        if (order.getLivetype().intValue() == 3){ //长租房，获得天数
            int days = Days.daysBetween(dateIn.withTimeAtStartOfDay(),dateOut.withTimeAtStartOfDay()).getDays();
            BigDecimal money = roomtype.getLongtermPrice().multiply(new BigDecimal(days)).add(roomtype.getDeposit());
            payorder.setPayAmount(money);
        }
        payorder.setOrderno(order.getOrderno());
        return Result.success("入住账单已经生成,客户支付成功后提交账单完成入住!",payorder);
    }

    @Override
    public Result<Object> checkOut(Integer id) throws Exception{
        //根据订单id获得订单
        Order order = orderDao.selectByPrimaryKey(id);
        if (order == null)
            return Result.error("未查询到订单信息!");
        //获得房间
        Room room = roomDao.selectByPrimaryKey(order.getRoomId());
        //生成退房账单
        Roomtype roomtype = roomtypeDao.selectByPrimaryKey(room.getRoomtypeId());
        if (roomtype.getDeposit() == null)
            roomtype.setDeposit(new BigDecimal(0));
        Payorder payorder = new Payorder();
        payorder.setHotelId(order.getHotelId());
        payorder.setOrderId(order.getId());
        payorder.setPayEvent("checkOut"); //设置支付事件，提交支付订单后更新房态以及入住状态
        payorder.setPayAmount(roomtype.getDeposit());
        //订单移动到历史记录
        //TODO
        return Result.success("退房账单已经生成!",payorder);
    }

    @Override
    public Result<Object> checkOut(Integer tId, String roomno) throws Exception {
        List<Room> rooms = roomDao.getAll(null,2,tId,roomno);
        if (rooms.isEmpty())
            return Result.error("未查询到房间信息!");
        Order order = orderDao.getOrderByRoomId(rooms.get(0).getId());
        if (order == null)
            return Result.error("未查询到订单信息!");
        return this.checkOut(order.getId());
    }

    @Override
    public Result<Object> checkOutByRoomId(Integer id) throws Exception {
        Room room = roomDao.selectByPrimaryKey(id);
        if (room == null)
            return Result.error("未查询到房间信息!");
        Order order = orderDao.getOrderByRoomId(id);
        if (order == null)
            return Result.error("未查询到订单信息!");
        return this.checkOut(order.getId());
    }

    @Override
    public Result<Object> extend(String count, Order order, Integer tId, String roomno) throws Exception {
        List<Order> orders = null;
        if (roomno != null && tId != null){
            List<Room> rooms = roomDao.getAll(null, null, tId, roomno);
            if (rooms.isEmpty())
                return Result.error("房间号或房型id有误!");
            order.setRoomId(rooms.get(0).getId());
            order.setRoomId(rooms.get(0).getRoomtypeId());
            orders = orderDao.searchSelective(order);
            if (orders.isEmpty())
                return Result.error("没有查询到订单!");
            if (3 == orders.get(0).getStatus())
                return Result.error("该订单已经退房!");
        }
        Order order1;
        if (orders != null)
            order1 = orders.get(0);
        else
            order1 = orderDao.selectByPrimaryKey(order.getId());
        //续房
        if (order1 == null)
            return Result.error("没有查询到订单!");
        Roomtype roomtype = roomtypeDao.selectByPrimaryKey(order1.getRoomtypeId());
        //生成账单
        Payorder payorder = new Payorder();
        payorder.setHotelId(order1.getHotelId());
        payorder.setOrderId(order1.getId());
        payorder.setPayEvent("extend");
        payorder.setPayInfo(count);
        if (order1.getLivetype().intValue() == 1){ //全天房，获得天数
            BigDecimal money = roomtype.getPrice().multiply(new BigDecimal(count));
            payorder.setPayAmount(money);
        }
        if (order1.getLivetype().intValue() == 2){ //钟点房，获得小时数
            BigDecimal money = roomtype.getHourPrice().multiply(new BigDecimal(count));
            payorder.setPayAmount(money);
        }
        if (order1.getLivetype().intValue() == 3){ //长租房，获得天数
            BigDecimal money = roomtype.getLongtermPrice().multiply(new BigDecimal(count));
            payorder.setPayAmount(money);
        }
        return Result.success("续房账单已经生成,客户支付成功后提交账单完成续房!",payorder);
    }

    @Override
    public Result<List<Order>> searchSelective(Order order) throws Exception {
        return Result.success(orderDao.searchSelective(order));
    }

    @Override
    public Result<List<Order>> searchByWechat(Integer status, String ordernos) throws Exception{
        return Result.success(orderDao.searchByWechat(status, ordernos));
    }

    @Override
    public Result<Order> getOrderByRoomno(Integer tId, String roomno) throws Exception{
        List<Room> rooms = roomDao.getAll(null, 2,tId,roomno);
        if (rooms.isEmpty())
            return Result.error("未查询到房间信息!");
        return Result.success(orderDao.getOrderByRoomId(rooms.get(0).getId()));
    }

    @Override
    public Result<Order> getOrderByRoomId(Integer id) throws Exception{
        Room room = roomDao.selectByPrimaryKey(id);
        if (room == null)
            return Result.error("未查询到房间信息!");
        return Result.success(orderDao.getOrderByRoomId(id));
    }

    @Override
    public Result<Order> getOrderByDevice(HttpServletRequest request, Order order) throws Exception{
        String []params = (String[]) request.getAttribute("params");
        order.setmId(Integer.parseInt(params[0]));
        order.setHotelId(Integer.parseInt(params[1]));
        order.setStatus((byte)1); //查询未入住状态的预订单
        List<Order> orders = orderDao.searchSelective(order);
        if(orders.isEmpty())
            return Result.success();
        return Result.success(orders.get(0));
    }

    @Override
    public Result<Object> checkInByDevice(HttpServletRequest request, Integer id) throws Exception {
        return checkIn(id);
    }

    @Override
    public Result<Object> checkStatus(HttpServletRequest request, Order order) throws Exception {
        String []params = (String[]) request.getAttribute("params");
        order.setmId(Integer.parseInt(params[0]));
        order.setHotelId(Integer.parseInt(params[1]));
        Long c = orderDao.checkStatus(order);
        if (c > 0)
            return Result.error("该用户已经在该酒店入住");
        return Result.success();
    }

    @Override
    public Result<Map<String, Object>> searchSelectivePage(Order order) throws Exception {
        Long total = orderDao.searchSelectivePageCount(order);
        Map<String, Object> map = new HashMap<>();
        map.put("pageNum", order.getPageNum());
        map.put("pageSize", order.getPageSize());
        map.put("total", total);
        if (order.getPageNum() <= 0)
            order.setPageNum(1);
        order.setPageNum((order.getPageNum() - 1) * order.getPageSize());
        List<Order> list = orderDao.searchSelectivePage(order);
        map.put("list", list);
        return Result.success(map);
    }
}
