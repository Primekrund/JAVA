package com.krund.hotel.manage.pay.wechat;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class WechatConfigs {
    private static Configuration configs;
    private static String appId;
    private static String appSecret;
    private static String mchId;
    private static String unifiedorderUrl;
    private static String refundUrl;
    private static String refundQuery;
    private static String notifyPay;
    private static String tradeType;
    private static String certPath;
    private static String successRequest;

    private WechatConfigs() {
        // No Constructor
    }

    // 根据文件名读取配置文件，文件后缀名必须为.properties
    public synchronized static void init(String filePath) {
        if (configs != null) {
            return;
        }

        try {
            configs = new PropertiesConfiguration(filePath);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

        if (configs == null) {
            throw new IllegalStateException("can`t find file by path:" + filePath);
        }

        appId = configs.getString("app_id");
        appSecret = configs.getString("app_secret");
        mchId = configs.getString("mch_id");
        unifiedorderUrl = configs.getString("unifiedorder_url");
        refundUrl = configs.getString("refund_url");
        refundQuery = configs.getString("refund_query");
        notifyPay = configs.getString("notify_pay");
        tradeType = configs.getString("trade_type");
        certPath = configs.getString("cert_path");
        successRequest = configs.getString("success_request");
    }

    public static Configuration getConfigs() {
        return configs;
    }

    public static String getAppId() {
        return appId;
    }

    public static String getAppSecret() {
        return appSecret;
    }

    public static String getMchId() {
        return mchId;
    }

    public static String getUnifiedorderUrl() {
        return unifiedorderUrl;
    }

    public static String getRefundUrl() {
        return refundUrl;
    }

    public static String getRefundQuery() {
        return refundQuery;
    }

    public static String getNotifyPay() {
        return notifyPay;
    }

    public static String getTradeType() {
        return tradeType;
    }

    public static String getCertPath() {
        return certPath;
    }

    public static String getSuccessRequest() {
        return successRequest;
    }
}
