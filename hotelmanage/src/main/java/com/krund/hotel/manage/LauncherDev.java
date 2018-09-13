package com.krund.hotel.manage;

import org.eclipse.jetty.server.Server;

/**
 * @program: iHotel
 * @description: web app启动类(开发用)
 * @author: Zhang Ziming
 * @create: 2018-04-27 10:15
 **/
public class LauncherDev {
    private static final String DEFAULT_CONTEXT_PATH = "/iHotel";

    public static void main(String[] args) {

        Server server = Launcher.createDevServer(DEFAULT_CONTEXT_PATH);
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
}
