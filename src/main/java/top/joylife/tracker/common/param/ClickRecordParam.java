package top.joylife.tracker.common.param;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class ClickRecordParam {

    /**
     * 访问的ip地址
     */
    private String ipAddress;

    /**
     * 从哪个页面链接跳转过来的
     */
    private String reffer;

    /**
     * 用户代理
     */
    private String userAgent;

    /**
     * 代理ip
     */
    private String proxyIp;

    /**
     * 点击时间
     */
    private Date clickTime;

    /**
     * offer点击时间
     */
    private Date offerClickTime;

    /**
     * 语言
     */
    private String language;

    /**
     * 转化时间
     */
    private Date convTime;

    /**
     * 设备生产商
     */
    private String manufacturer;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备型号
     */
    private String deviceModel;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 操作系统
     */
    private String operatingSystem;

    /**
     * 系统语言
     */
    private String systemLanguage;

    /**
     * 显示大小
     */
    private String displaySize;

    /**
     * 网速
     */
    private String dataSpeed;

    /**
     * 流量类型,popup,popunder
     */
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
    private String countryCode;

    /**
     * 国家名字
     */
    private String countryName;

    /**
     * 省code
     */
    private String provinceCode;

    /**
     * 省名字
     */
    private String provinceName;

    /**
     * 市code
     */
    private String cityCode;

    /**
     * 市名字
     */
    private String cityName;

    /**
     * 区code
     */
    private String countyCode;

    /**
     * 县名字
     */
    private String countyName;

    /**
     * 每点击出价
     */
    private Integer payPerClick;

    /**
     * 质量
     */
    private String quality;

}
