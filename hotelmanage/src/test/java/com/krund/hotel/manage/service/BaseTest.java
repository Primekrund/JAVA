package com.krund.hotel.manage.service;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 单元测试基类
 * @author Zhang Ziming
 */
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
public abstract class BaseTest extends AbstractJUnit4SpringContextTests {
}
