package top.joylife.tracker.dao.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "click_record")
public class ClickRecord {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

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

    /**
     * 记录创建时间
     */
    @Column(name = "date_create")
    private Date dateCreate;

    /**
     * 记录更新时间
     */
    @Column(name = "date_update")
    private Date dateUpdate;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取campaignId
     *
     * @return campaign_id - campaignId
     */
    public Integer getCampaignId() {
        return campaignId;
    }

    /**
     * 设置campaignId
     *
     * @param campaignId campaignId
     */
    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    /**
     * 获取流量源id
     *
     * @return traffic_id - 流量源id
     */
    public Integer getTrafficId() {
        return trafficId;
    }

    /**
     * 设置流量源id
     *
     * @param trafficId 流量源id
     */
    public void setTrafficId(Integer trafficId) {
        this.trafficId = trafficId;
    }

    /**
     * 获取访问的ip地址
     *
     * @return ip_address - 访问的ip地址
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * 设置访问的ip地址
     *
     * @param ipAddress 访问的ip地址
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * 获取从哪个页面链接跳转过来的
     *
     * @return reffer - 从哪个页面链接跳转过来的
     */
    public String getReffer() {
        return reffer;
    }

    /**
     * 设置从哪个页面链接跳转过来的
     *
     * @param reffer 从哪个页面链接跳转过来的
     */
    public void setReffer(String reffer) {
        this.reffer = reffer;
    }

    /**
     * 获取用户代理
     *
     * @return user_agent - 用户代理
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * 设置用户代理
     *
     * @param userAgent 用户代理
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * 获取代理ip
     *
     * @return proxy_ip - 代理ip
     */
    public String getProxyIp() {
        return proxyIp;
    }

    /**
     * 设置代理ip
     *
     * @param proxyIp 代理ip
     */
    public void setProxyIp(String proxyIp) {
        this.proxyIp = proxyIp;
    }

    /**
     * 获取点击时间
     *
     * @return click_time - 点击时间
     */
    public Date getClickTime() {
        return clickTime;
    }

    /**
     * 设置点击时间
     *
     * @param clickTime 点击时间
     */
    public void setClickTime(Date clickTime) {
        this.clickTime = clickTime;
    }

    /**
     * 获取offer点击时间
     *
     * @return offer_click_time - offer点击时间
     */
    public Date getOfferClickTime() {
        return offerClickTime;
    }

    /**
     * 设置offer点击时间
     *
     * @param offerClickTime offer点击时间
     */
    public void setOfferClickTime(Date offerClickTime) {
        this.offerClickTime = offerClickTime;
    }

    /**
     * 获取语言
     *
     * @return language - 语言
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 设置语言
     *
     * @param language 语言
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 获取转化时间
     *
     * @return conv_time - 转化时间
     */
    public Date getConvTime() {
        return convTime;
    }

    /**
     * 设置转化时间
     *
     * @param convTime 转化时间
     */
    public void setConvTime(Date convTime) {
        this.convTime = convTime;
    }

    /**
     * 获取设备生产商
     *
     * @return manufacturer - 设备生产商
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * 设置设备生产商
     *
     * @param manufacturer 设备生产商
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * 获取设备名称
     *
     * @return device_name - 设备名称
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * 设置设备名称
     *
     * @param deviceName 设备名称
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * 获取设备型号
     *
     * @return device_model - 设备型号
     */
    public String getDeviceModel() {
        return deviceModel;
    }

    /**
     * 设置设备型号
     *
     * @param deviceModel 设备型号
     */
    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    /**
     * 获取设备类型
     *
     * @return device_type - 设备类型
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * 设置设备类型
     *
     * @param deviceType 设备类型
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * 获取操作系统
     *
     * @return operating_system - 操作系统
     */
    public String getOperatingSystem() {
        return operatingSystem;
    }

    /**
     * 设置操作系统
     *
     * @param operatingSystem 操作系统
     */
    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    /**
     * 获取系统语言
     *
     * @return system_language - 系统语言
     */
    public String getSystemLanguage() {
        return systemLanguage;
    }

    /**
     * 设置系统语言
     *
     * @param systemLanguage 系统语言
     */
    public void setSystemLanguage(String systemLanguage) {
        this.systemLanguage = systemLanguage;
    }

    /**
     * 获取显示大小
     *
     * @return display_size - 显示大小
     */
    public String getDisplaySize() {
        return displaySize;
    }

    /**
     * 设置显示大小
     *
     * @param displaySize 显示大小
     */
    public void setDisplaySize(String displaySize) {
        this.displaySize = displaySize;
    }

    /**
     * 获取网速
     *
     * @return data_speed - 网速
     */
    public String getDataSpeed() {
        return dataSpeed;
    }

    /**
     * 设置网速
     *
     * @param dataSpeed 网速
     */
    public void setDataSpeed(String dataSpeed) {
        this.dataSpeed = dataSpeed;
    }

    /**
     * 获取流量类型,popup,popunder
     *
     * @return traffic_type - 流量类型,popup,popunder
     */
    public String getTrafficType() {
        return trafficType;
    }

    /**
     * 设置流量类型,popup,popunder
     *
     * @param trafficType 流量类型,popup,popunder
     */
    public void setTrafficType(String trafficType) {
        this.trafficType = trafficType;
    }

    /**
     * 获取平台
     *
     * @return platform - 平台
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * 设置平台
     *
     * @param platform 平台
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * 获取浏览器
     *
     * @return browser - 浏览器
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * 设置浏览器
     *
     * @param browser 浏览器
     */
    public void setBrowser(String browser) {
        this.browser = browser;
    }

    /**
     * 获取运营商
     *
     * @return isp - 运营商
     */
    public String getIsp() {
        return isp;
    }

    /**
     * 设置运营商
     *
     * @param isp 运营商
     */
    public void setIsp(String isp) {
        this.isp = isp;
    }

    /**
     * 获取国家编码
     *
     * @return country_code - 国家编码
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * 设置国家编码
     *
     * @param countryCode 国家编码
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * 获取国家名字
     *
     * @return country_name - 国家名字
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * 设置国家名字
     *
     * @param countryName 国家名字
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * 获取省code
     *
     * @return province_code - 省code
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * 设置省code
     *
     * @param provinceCode 省code
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * 获取省名字
     *
     * @return province_name - 省名字
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * 设置省名字
     *
     * @param provinceName 省名字
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * 获取市code
     *
     * @return city_code - 市code
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * 设置市code
     *
     * @param cityCode 市code
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * 获取市名字
     *
     * @return city_name - 市名字
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 设置市名字
     *
     * @param cityName 市名字
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * 获取区code
     *
     * @return county_code - 区code
     */
    public String getCountyCode() {
        return countyCode;
    }

    /**
     * 设置区code
     *
     * @param countyCode 区code
     */
    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    /**
     * 获取县名字
     *
     * @return county_name - 县名字
     */
    public String getCountyName() {
        return countyName;
    }

    /**
     * 设置县名字
     *
     * @param countyName 县名字
     */
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    /**
     * 获取每点击出价
     *
     * @return pay_per_click - 每点击出价
     */
    public Integer getPayPerClick() {
        return payPerClick;
    }

    /**
     * 设置每点击出价
     *
     * @param payPerClick 每点击出价
     */
    public void setPayPerClick(Integer payPerClick) {
        this.payPerClick = payPerClick;
    }

    /**
     * 获取质量
     *
     * @return quality - 质量
     */
    public String getQuality() {
        return quality;
    }

    /**
     * 设置质量
     *
     * @param quality 质量
     */
    public void setQuality(String quality) {
        this.quality = quality;
    }

    /**
     * 获取记录创建时间
     *
     * @return date_create - 记录创建时间
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     * 设置记录创建时间
     *
     * @param dateCreate 记录创建时间
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    /**
     * 获取记录更新时间
     *
     * @return date_update - 记录更新时间
     */
    public Date getDateUpdate() {
        return dateUpdate;
    }

    /**
     * 设置记录更新时间
     *
     * @param dateUpdate 记录更新时间
     */
    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
}