package com.eventdoc.sample;

import com.eventdoc.annotation.EventDoc;

@EventDoc(eventName = "userLogin", eventDesc = "用户登陆信息埋点", eventCondition = "用户登陆时推送", eventPoint = "服务端")
public class LoginEvent {

    /**
     * 用户名
     */
    private String username;

    /**
     * 登陆时间
     */
    private String loginTime;

    /**
     * 登陆地址
     */
    private String loginAddress;
}

