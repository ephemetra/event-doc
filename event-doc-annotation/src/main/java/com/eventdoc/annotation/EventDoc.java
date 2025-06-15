package com.eventdoc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface EventDoc {

    /**
     * 事件名称
     */
    String eventName() default "";

    /**
     * 事件描述
     */
    String eventDesc() default "";

    /**
     * 触发时机
     */
    String eventCondition() default "";

    /**
     * 埋点端
     */
    String eventPoint() default "服务端";
}
