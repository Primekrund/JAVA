package com.krund.hotel.manage.pay.wechat;

import com.alibaba.fastjson.JSON;
import com.krund.hotel.manage.pay.util.SignUtil;
import com.krund.hotel.manage.pay.util.XmlUtil;
import com.krund.hotel.manage.pay.wechat.entity.WechatPayNotify;
import com.krund.hotel.manage.pay.wechat.entity.WechatRefund;
import com.krund.hotel.manage.pay.wechat.entity.WechatRefundQuery;
import com.krund.hotel.manage.pay.wechat.entity.WechatUnifiedOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.TreeMap;


/**
 * 微信交易
 */
@Component
public class WechatTrade {
    static {
        WechatConfigs.init("properties/wechat.properties");
    }

    private static Logger logger = LoggerFactory.getLogger(WechatTrade.class);

    /**
     * 微信统一下单
     * @param unifiedOrder 要下单的内容
     * @return 返回预下单结果
     */
    public TreeMap<String,String> unifiedOrderRequest(WechatUnifiedOrder unifiedOrder){
        WechatUnifiedOrder.Response response =  WechatClientHelper.getInstance().unifiedOrder(unifiedOrder);
        TreeMap<String,String> prepareNativePay = new TreeMap<>();
        if(response.getResult_code() == null){
            prepareNativePay.put("msg",response.getReturn_msg());
            return prepareNativePay;
        }
        if(response.getResult_code().equals("FAIL")){
            prepareNativePay.put("msg",response.getReturn_msg());
        }
        if (response.getResult_code().equals(WechatConfigs.getSuccessRequest())){
            prepareNativePay.put("appid", WechatConfigs.getAppId());
            prepareNativePay.put("partnerid", WechatConfigs.getMchId());
            prepareNativePay.put("noncestr", WechatClientHelper.getInstance().nonce_str(16));
            prepareNativePay.put("package", "Sign=WXPay");
            prepareNativePay.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
            prepareNativePay.put("prepayid",response.getPrepay_id()); //预下单id，H5支付使用
            prepareNativePay.put("codeUrl",response.getCode_url()); //二维码URL，扫码支付使用
            prepareNativePay.put("sign", WechatClientHelper.getInstance().sign(prepareNativePay));
        }
        return prepareNativePay;
    }

    /**
     * 微信统一下单 JSAPI
     * @param unifiedOrder 要下单的内容
     * @return 返回预下单结果
     */
    public TreeMap<String,String> unifiedOrderRequestForJSAPI(WechatUnifiedOrder unifiedOrder){
        WechatUnifiedOrder.Response response =  WechatClientHelper.getInstanceForJSAPI().unifiedOrder(unifiedOrder);
        TreeMap<String,String> prepareJSAPIPay = new TreeMap<>();
        if(response.getResult_code() == null){
            prepareJSAPIPay.put("msg",response.getReturn_msg());
            return prepareJSAPIPay;
        }
        if(response.getResult_code().equals("FAIL")){
            prepareJSAPIPay.put("msg",response.getReturn_msg());
        }
        if (response.getResult_code().equals(WechatConfigs.getSuccessRequest())){
            prepareJSAPIPay.put("appId", WechatConfigs.getAppId());
            prepareJSAPIPay.put("nonceStr", unifiedOrder.getNonce_str());
            prepareJSAPIPay.put("package", "prepay_id=" + response.getPrepay_id());
            prepareJSAPIPay.put("signType","MD5");
            prepareJSAPIPay.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
            prepareJSAPIPay.put("paySign", WechatClientHelper.getInstanceForJSAPI().sign(prepareJSAPIPay).toUpperCase());
        }
        return prepareJSAPIPay;
    }

    /**
     * 微信退款请求
     * @param refund 退款请求参数
     * @return 返回参数(同步接口,直接返回),只有return_code和result_code都成功则退款成功
     */
    public WechatRefund.Response refundRequest(WechatRefund refund){
        WechatRefund.Response response = WechatClientHelper.getInstance().refund(refund);
        return response;
    }

    /**
     * 微信退款请求 JSAPI
     * @param refund 退款请求参数
     * @return 返回参数(同步接口,直接返回),只有return_code和result_code都成功则退款成功
     */
    public WechatRefund.Response refundRequestForJSAPI(WechatRefund refund){
        WechatRefund.Response response = WechatClientHelper.getInstanceForJSAPI().refund(refund);
        return response;
    }

    /**
     * 微信退款查询请求
     * @param refund 退款请求参数
     * @return 返回参数(同步接口,直接返回),只有return_code和result_code都成功则查询成功
     */
    public WechatRefundQuery.Response refundQueryRequest(WechatRefundQuery refund){
        WechatRefundQuery.Response response = WechatClientHelper.getInstance().refundQuery(refund);
        return response;
    }

    /**
     * 微信回调验签
     * @param request  回调请求
     * @return true成功
     */
    public boolean verifyNotify(HttpServletRequest request, HttpServletResponse response){
        try {
            InputStream inputStream = request.getInputStream();
            WechatPayNotify notice = XmlUtil.xmlToBean(inputStream, WechatPayNotify.class);
            if (notice == null) return false;
            logger.debug("微信回调参数:"+ JSON.toJSONString(notice));
            String sign = WechatClientHelper.getInstance().sign(SignUtil.bean2TreeMap(notice));
            //比较验签结果须转换成大写比较，因为微信返回的签名是大写
            boolean ischeck = (sign.toUpperCase()).equals(notice.getSign());
            logger.debug("微信验签结果:"+ischeck);
            String outTradeNo = notice.getOut_trade_no();
            if(!ischeck) { // JSAPI验签
                sign = WechatClientHelper.getInstanceForJSAPI().sign(SignUtil.bean2TreeMap(notice));
                ischeck = (sign.toUpperCase()).equals(notice.getSign());
            }
            if(ischeck){
                //通知微信验签成功
                PrintWriter writer = response.getWriter();
                writer.print("<xml>\n" +
                        "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                        "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                        "</xml>");
                writer.flush();
                writer.close();
            }
            return ischeck;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
