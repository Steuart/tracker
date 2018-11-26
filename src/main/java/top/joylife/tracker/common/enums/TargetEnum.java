package top.joylife.tracker.common.enums;

import lombok.Getter;

/**
 * 统计指标枚举值
 */
@Getter
public enum TargetEnum {
    ipAddress("ipAddress","来源IP"),
    reffer("reffer","跳转链接"),
    userAgent("userAgent","用户代理"),
    proxyIp("proxyIp","代理IP"),
    clickTime("clickTime","链接点击时间"),
    offerClickTime("offerClickTime","offer点击时间"),
    language("language","语言"),
    convTime("convTime","转化时间"),
    manufacturer("manufacturer","设备生产商"),
    deviceName("deviceName","设备名称"),
    deviceModel("deviceModel","设备型号"),
    deviceType("deviceType","设备类型"),
    operatingSystem("operatingSystem","操作系统"),
    systemLanguage("systemLanguage","系统语言"),
    displaySize("displaySize","显示器大小"),
    dataSpeed("dataSpeed","网速"),
    trafficType("trafficType","流量类型"),
    platform("platform","平台"),
    browser("browser","浏览器"),
    isp("isp","网络运营商"),
    countryCode("countryCode","国家编码"),
    countryName("countryName","国家名字"),
    provinceCode("provinceCode","省编码"),
    provinceName("provinceName","省名字"),
    cityCode("cityCode","市编码"),
    cityName("cityName","市名字"),
    countyCode("countyCode","区县编码"),
    countyName("countyName","区县名字"),
    payPerClick("payPerClick","每点击出价"),
    quality("quality","流量质量");

    private String code;
    private String name;

    TargetEnum(String code, String name){
        this.code = code;
        this.name = name;
    }
}
