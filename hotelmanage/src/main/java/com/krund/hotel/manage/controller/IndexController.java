package com.krund.hotel.manage.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
public class IndexController {
    @Value("#{index['index.uri']}")
    private String uri;

    /**
    * @Description: 启动成功后显示的提示页面
    * @Param: [request, response]
    * @return: void
    * @Author: Zhang Ziming
    * @Date: 2018/4/12
    */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public void index(HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("GB2312");
        PrintWriter writer = response.getWriter();
        String html = "<html>\n" +
                "<head>\n" +
                "    <title>克路德智慧酒店PMS</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n<p>服务端启动成功！请通过api接口调用</p><br /><p>请参阅：</p><a href='" + uri + "/swagger-ui.html'>API文档</a><p>|</p><a href='" + uri + "/druid'>数据库监控</a>" +
                "</body>\n" +
                "</html>";
        writer.print(html);
        writer.flush();
        writer.close();
    }
}
