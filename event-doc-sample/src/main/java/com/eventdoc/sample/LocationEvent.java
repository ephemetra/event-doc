package com.eventdoc.sample;

import com.eventdoc.annotation.EventDoc;

import java.time.LocalDateTime;
import java.util.Date;

@EventDoc(eventName = "userLocation", eventDesc = "定位信息埋点", eventCondition = "客户端定时推送", eventPoint = "客户端")
public class LocationEvent {

    /**
     * 设备编号
     */
    private String deviceNo;

    /**
     * 设备编号
     */
    private LocalDateTime locatedTime;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;
}

