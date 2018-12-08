package top.joylife.tracker.common.enums;

import lombok.Getter;

@Getter
public enum  QuotaEnum {
    PAYOUT("payout","支付金额",QuotaGroupEnum.TRANSFER_DATA),
    CLICK_RECORD_UUID("click_record_uuid","访问记录ID",QuotaGroupEnum.CLICK_DATA),
    IP_ADDRESS("ip_address","Ip Address",QuotaGroupEnum.CLICK_DATA),
    REFERER("referer","referer",QuotaGroupEnum.CLICK_DATA),
    USER_AGENT("user_agent","User Agent",QuotaGroupEnum.CLICK_DATA),
    PROXY_IP("proxy_ip","Proxy IP",QuotaGroupEnum.CLICK_DATA),
    MOD_USER_AGENT("mod_user_agent","Mod.User Agent",QuotaGroupEnum.CLICK_DATA),
    CLICK_DATE("click_date","点击时间",QuotaGroupEnum.TIME_DATA),
    OFFER_CLICK_DATE("offer_click_date","OFFER点击时间",QuotaGroupEnum.TIME_DATA),
    CONV_DATE("conv_date","转化时间",QuotaGroupEnum.TIME_DATA),
    MANUFACTURER("manufacturer","生产商",QuotaGroupEnum.DEVICE_DATA),
    DEVICE_NAME("device_name","设备名字",QuotaGroupEnum.DEVICE_DATA),
    DEVICE_MODEL("device_model","设备型号",QuotaGroupEnum.DEVICE_DATA),
    DEVICE_TYPE("device_type","设备类型",QuotaGroupEnum.DEVICE_DATA),
    OPERATING_SYSTEM("operating_system","操作系统",QuotaGroupEnum.DEVICE_DATA),
    DISPLAY_SIZE("display_size","显示尺寸",QuotaGroupEnum.DEVICE_DATA),
    PLATFORM("platform","平台",QuotaGroupEnum.DEVICE_DATA),
    BROWSER("browser","浏览器",QuotaGroupEnum.DEVICE_DATA),
    ISP("isp","运营商",QuotaGroupEnum.TRAFFIC_DATA);

    private String code;
    private String name;
    private QuotaGroupEnum groupEnum;

    QuotaEnum(String code, String desc,QuotaGroupEnum groupEnum){
        this.code = code;
        this.name = desc;
        this.groupEnum = groupEnum;
    }
}
