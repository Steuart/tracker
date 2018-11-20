package top.joylife.tracker.dao.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "click_record")
@Data
public class ClickRecord extends BaseEntity{

    @Column(name = "uuid")
    private String uuid;

    /**
     * campaignId
     */
    @Column(name = "campaign_id")
    private Integer campaignId;

    /**
     * 流量源id
     */
    @Column(name = "traffic_id")
    private Integer trafficId;

    @Column(name = "network_id")
    private Integer networkId;

    @Column(name = "offer_id")
    private Integer offerId;

    /**
     * 访问的ip地址
     */
    @Column(name = "ip_address")
    private String ipAddress;

    /**
     * 从哪个页面链接跳转过来的
     */
    private String reffer;

    /**
     * 用户代理
     */
    @Column(name = "user_agent")
    private String userAgent;

    /**
     * 代理ip
     */
    @Column(name = "proxy_ip")
    private String proxyIp;

    /**
     * 点击时间
     */
    @Column(name = "click_time")
    private Date clickTime;

    /**
     * offer点击时间
     */
    @Column(name = "offer_click_time")
    private Date offerClickTime;

    /**
     * 语言
     */
    private String language;

    /**
     * 转化时间
     */
    @Column(name = "conv_time")
    private Date convTime;

    /**
     * 设备生产商
     */
    private String manufacturer;

    /**
     * 设备名称
     */
    @Column(name = "device_name")
    private String deviceName;

    /**
     * 设备型号
     */
    @Column(name = "device_model")
    private String deviceModel;

    /**
     * 设备类型
     */
    @Column(name = "device_type")
    private String deviceType;

    /**
     * 操作系统
     */
    @Column(name = "operating_system")
    private String operatingSystem;

    /**
     * 系统语言
     */
    @Column(name = "system_language")
    private String systemLanguage;

    /**
     * 显示大小
     */
    @Column(name = "display_size")
    private String displaySize;

    /**
     * 网速
     */
    @Column(name = "data_speed")
    private String dataSpeed;

    /**
     * 流量类型,popup,popunder
     */
    @Column(name = "traffic_type")
    private String trafficType;

    /**
     * 平台
     */
    private String platform;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 运营商
     */
    private String isp;

    /**
     * 国家编码
     */
    @Column(name = "country_code")
    private String countryCode;

    /**
     * 国家名字
     */
    @Column(name = "country_name")
    private String countryName;

    /**
     * 省code
     */
    @Column(name = "province_code")
    private String provinceCode;

    /**
     * 省名字
     */
    @Column(name = "province_name")
    private String provinceName;

    /**
     * 市code
     */
    @Column(name = "city_code")
    private String cityCode;

    /**
     * 市名字
     */
    @Column(name = "city_name")
    private String cityName;

    /**
     * 区code
     */
    @Column(name = "county_code")
    private String countyCode;

    /**
     * 县名字
     */
    @Column(name = "county_name")
    private String countyName;

    /**
     * 每点击出价
     */
    @Column(name = "pay_per_click")
    private Integer payPerClick;

    /**
     * 质量
     */
    private String quality;
}