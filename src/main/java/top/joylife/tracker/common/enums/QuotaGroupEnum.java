package top.joylife.tracker.common.enums;

import lombok.Getter;

/**
 * 指标分组枚举值
 */
@Getter
public enum QuotaGroupEnum {
    BASIC("basic","基本数据"),
    CLICK_DATA("clickData","点击数据"),
    TIME_DATA("timeData","时间数据"),
    campaign_data("campaignData","营销数据"),
    DEVICE_DATA("deviceData","设备数据"),
    TRAFFIC_DATA("trafficData","流量数据"),
    TRANSFER_DATA("transferData","转化数据");

    private String code;
    private String name;

    QuotaGroupEnum(String code,String name){
        this.code = code;
        this.name = name;
    }
}
