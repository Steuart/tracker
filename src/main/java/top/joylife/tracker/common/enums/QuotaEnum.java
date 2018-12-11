package top.joylife.tracker.common.enums;

import lombok.Getter;

@Getter
public enum  QuotaEnum {
    PAYOUT("payout","支付金额",QuotaGroupEnum.CLICK_DATA),
    CLICK_RECORD_UUID("click_record_uuid","访问记录ID",QuotaGroupEnum.CLICK_DATA),
    //HTTP
    ACCEPT("accept","Accept",QuotaGroupEnum.HTTP),
    ACCEPT_CHARSET("accept-charset","Accept-Charset",QuotaGroupEnum.HTTP),
    ACCEPT_ENCODING("accept-encoding","Accept-Encoding",QuotaGroupEnum.HTTP),
    ACCEPT_LANGUAGE("accept-language","Accept-Language",QuotaGroupEnum.HTTP),
    ACCEPT_DATETIME("accept-datetime","Accept-Datetime",QuotaGroupEnum.HTTP),
    AUTHORIZATION("authorization","Authorization",QuotaGroupEnum.HTTP),
    CACHE_CONTROL("cache-control","Cache-Control",QuotaGroupEnum.HTTP),
    CONNECTION("connection","Connection",QuotaGroupEnum.HTTP),
    COOKIE("cookie","Cookie",QuotaGroupEnum.HTTP),
    CONTENT_LENGTH("content-length","Content-Length",QuotaGroupEnum.HTTP),
    CONTENT_TYPE("content-type","Content-Type",QuotaGroupEnum.HTTP),
    DATE("date","Date",QuotaGroupEnum.HTTP),
    EXPECT("expect","expect",QuotaGroupEnum.HTTP),
    FROM("from","From",QuotaGroupEnum.HTTP),
    HOST("host","Host",QuotaGroupEnum.HTTP),
    MAX_FORWARDS("max-forwards","Max-Forwards",QuotaGroupEnum.HTTP),
    ORIGIN("origin","Origin",QuotaGroupEnum.HTTP),
    Pragma("pragma","Pragma",QuotaGroupEnum.HTTP),
    PROXY_AUTHORIZATION("proxy-authorization","Proxy-Authorization",QuotaGroupEnum.HTTP),
    REFERER("referer","Referer",QuotaGroupEnum.HTTP),
    TE("te","TE",QuotaGroupEnum.HTTP),
    USER_AGENT("user-agent","User-Agent",QuotaGroupEnum.HTTP),
    UPGRADE("upgrade","Upgrade",QuotaGroupEnum.HTTP),
    VIA("via","Via",QuotaGroupEnum.HTTP),
    WARNING("warning","Warning",QuotaGroupEnum.HTTP),
    X_FORWARDED_FOR("x-forwarded-for","X-Forwarded-For",QuotaGroupEnum.HTTP),
    X_Forwarded_Host("x-forwarded-host","X-Forwarded-Host",QuotaGroupEnum.HTTP),
    X_Forwarded_Proto("x-forwarded-proto","X-Forwarded-Proto",QuotaGroupEnum.HTTP),
    FRONT_END_HTTPS("front-end-https","Front-End-Https",QuotaGroupEnum.HTTP),
    X_HTTP_METHOD_OVERRIDE("x-http-method-override","X-Http-Method-Override",QuotaGroupEnum.HTTP),
    X_ATT_DEVICEID("x-att-deviceid","X-ATT-DeviceId",QuotaGroupEnum.HTTP),
    X_WAP_PROFILE("x-wap-profile","X-Wap-Profile",QuotaGroupEnum.HTTP),
    PROXY_CONNECTION("proxy-connection","Proxy-Connection",QuotaGroupEnum.HTTP),
    REMOTE_IP("remote-address","remote-address",QuotaGroupEnum.HTTP),
    LOCALE("locale","Locale",QuotaGroupEnum.HTTP),
    ;

    private String code;
    private String name;
    private QuotaGroupEnum groupEnum;

    QuotaEnum(String code, String desc,QuotaGroupEnum groupEnum){
        this.code = code;
        this.name = desc;
        this.groupEnum = groupEnum;
    }
}
