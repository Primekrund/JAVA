package com.krund.hotel.manage;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;
import java.net.URL;
import java.security.ProtectionDomain;

/**
 * @program: iHotel
 * @description: web app启动类
 * @author: Zhang Ziming
 * @create: 2018-04-25 10:53
 * 内嵌jetty配置方法参见 http://git.eclipse.org/c/jetty/org.eclipse.jetty.project.git/tree/examples/embedded/src/main/java/org/eclipse/jetty/embedded/LikeJettyXml.java
 **/
public class Launcher {
    public static final int DEFAULT_PORT = 8083;
    public static final int DEFAULT_HTTPS_PORT = 443;
    private static final String DEFAULT_APP_CONTEXT_PATH = "src/main/webapp";
    private static final String DEFAULT_HTTPS_APP_CONTEXT_PATH = "/iHotel"; //maven-shade-plugin打包后默认webapp路径为 /iHotel

    public static void main(String[] args) {

        Server server = createJettyServer();
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                server.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
    * @Description: 创建jetty服务器，用于生产环境，开发中不要调用此方法
    * @Param: [port, contextPath]
    * @return: org.eclipse.jetty.server.Server
    * @Author: Zhang Ziming
    * @Date: 2018/4/25
    */
    public static Server createJettyServer() {

        Server server = new Server();
        server.setStopAtShutdown(true);
        // HTTP Configuration
        HttpConfiguration http_config = new HttpConfiguration();
        http_config.setSecureScheme("https");
        http_config.setSecurePort(8443);
        http_config.setOutputBufferSize(32768);
        http_config.setRequestHeaderSize(8192);
        http_config.setResponseHeaderSize(8192);
        http_config.setSendServerVersion(true);
        http_config.setSendDateHeader(false);
        // httpConfig.addCustomizer(new ForwardedRequestCustomizer());
        // === jetty-http.xml ===
        ServerConnector http = new ServerConnector(server,
                new HttpConnectionFactory(http_config));
        http.setPort(DEFAULT_PORT);
        http.setIdleTimeout(30000);
        server.addConnector(http);

        ProtectionDomain protectionDomain = Launcher.class.getProtectionDomain();
        URL location = protectionDomain.getCodeSource().getLocation();
        String warFile = location.toExternalForm();

        WebAppContext context = new WebAppContext(warFile, DEFAULT_HTTPS_APP_CONTEXT_PATH);
        context.setServer(server);

        // 设置work dir,war包将解压到该目录，jsp编译后的文件也将放入其中。
        String currentDir = new File(location.getPath()).getParent();
        File workDir = new File(currentDir, "work");
        context.setTempDirectory(workDir);

        server.setHandler(context);
        return server;

    }

    /**
    * @Description: 开发时调用，启动jetty服务器
    * @Param: [contextPath]
    * @return: org.eclipse.jetty.server.Server
    * @Author: Zhang Ziming
    * @Date: 2018/4/27
    */
    public static Server createDevServer(String contextPath) {

        Server server = new Server();
        server.setStopAtShutdown(true);

        ServerConnector connector = new ServerConnector(server);
        // 设置服务端口
        connector.setPort(DEFAULT_PORT);
        connector.setReuseAddress(false);
        server.setConnectors(new Connector[] {connector});

        // 设置web资源根路径以及访问web的根路径
        WebAppContext webAppCtx = new WebAppContext(DEFAULT_APP_CONTEXT_PATH, contextPath);
        webAppCtx.setDescriptor(DEFAULT_APP_CONTEXT_PATH + "/WEB-INF/web.xml");
        webAppCtx.setResourceBase(DEFAULT_APP_CONTEXT_PATH);
        webAppCtx.setClassLoader(Thread.currentThread().getContextClassLoader());
        server.setHandler(webAppCtx);

        return server;
    }
}
