package com.krund.hotel.manage.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 控制访问频率注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
//最高优先级
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface RequestLimit {
    /**
     *
     * 允许访问的次数，默认值1
     */
    int count() default 10;

    /**
     *
     * 时间段，单位为毫秒，默认值50
     */
    long time() default 50;
}
