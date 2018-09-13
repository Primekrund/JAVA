package com.krund.hotel.manage.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http请求工具类
 *
 * @author Zhang Ziming
 */
public class HttpClientUtil {
    //HTTP连接管理器
    private static final PoolingHttpClientConnectionManager HTTP_CLIENT_CONNECTION_MANAGER;

    //可关闭的HttpClient实例
    private static final CloseableHttpClient HTTP_CLIENT;

    //在静态块中初始化HttpClient实例，默认连接超时时间1分钟
    static {
        HTTP_CLIENT_CONNECTION_MANAGER = new PoolingHttpClientConnectionManager(RegistryBuilder.<ConnectionSocketFactory> create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", SSLConnectionSocketFactory.getSocketFactory()).build());
        HTTP_CLIENT_CONNECTION_MANAGER.setDefaultMaxPerRoute(100);
        HTTP_CLIENT_CONNECTION_MANAGER.setMaxTotal(200);
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(60000).setConnectTimeout(60000).setSocketTimeout(60000).build();
        HTTP_CLIENT = HttpClientBuilder.create().setConnectionManager(HTTP_CLIENT_CONNECTION_MANAGER).setDefaultRequestConfig(requestConfig).build();
    }

    /**
     * 不可实例化
     */
    private HttpClientUtil() {
    }

    /**
     * get请求
     *
     * @param uri 资源地址
     * @return
     */
    public static synchronized String get(String uri) throws Exception {
        CloseableHttpResponse response;
        // 创建httpget.
        HttpGet httpget = new HttpGet(uri);
        // 执行get请求.
        response = HTTP_CLIENT.execute(httpget);
        HttpEntity entity = response.getEntity();
        if (entity != null) return EntityUtils.toString(entity, "UTF-8");
        return null;
    }

    /**
     * post请求
     *
     * @param uri          资源地址
     * @param parameterMap
     * @return
     */
    public static synchronized String post(String uri, Map<String, Object> parameterMap) throws Exception {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if (parameterMap != null) {
            for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
                String name = entry.getKey();
                String value = String.valueOf(entry.getValue());
                if (StringUtils.isNotEmpty(name)) {
                    nameValuePairs.add(new BasicNameValuePair(name, value));
                }
            }
        }
        CloseableHttpResponse response = null;
        // 创建httppost
        HttpPost httppost = new HttpPost(uri);
        UrlEncodedFormEntity uefEntity;
        uefEntity = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
        httppost.setEntity(uefEntity);
        response = HTTP_CLIENT.execute(httppost);
        HttpEntity entity = response.getEntity();
        if (entity != null) return EntityUtils.toString(entity, "UTF-8");
        return null;
    }
}
