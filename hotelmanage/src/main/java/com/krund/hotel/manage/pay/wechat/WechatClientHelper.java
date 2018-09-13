package com.krund.hotel.manage.pay.wechat;

/**
 * 微信支付配置
 */
public final class WechatClientHelper {
    /**
     * 不可实例化
     */
    private WechatClientHelper(){}

    private volatile static WechatClient wechatClient = null;

    private volatile static WechatClient wechatClientForJSAPI = null;

    /**
     * 双重锁单例
     * @return WechatClient实例
     */
    public static WechatClient getInstance(){
        if (wechatClient == null){
            synchronized (WechatClientHelper.class){
                if (wechatClient == null){
                    return new WechatClient(WechatConfigs.getAppId(),WechatConfigs.getMchId(),WechatConfigs.getAppSecret(),WechatConfigs.getTradeType());
                }
            }
        }
        return wechatClient;
    }

    /**
     * 双重锁单例JSAPI
     * @return wechatClientForJSAPI
     */
    public static WechatClient getInstanceForJSAPI(){
        if (wechatClientForJSAPI == null){
            synchronized (WechatClientHelper.class){
                if (wechatClientForJSAPI == null){
                    return new WechatClient(WechatConfigs.getAppId(),WechatConfigs.getMchId(),WechatConfigs.getAppSecret(),"JSAPI");
                }
            }
        }
        return wechatClientForJSAPI;
    }
}
