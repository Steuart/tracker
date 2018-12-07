package top.joylife.tracker.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "campaign")
@Data
public class Campaign extends BaseEntity{


    /**
     * 流量id
     */
    @Column(name = "traffic_id")
    private Integer trafficId;

    /**
     * 联盟id
     */
    @Column(name = "network_id")
    private Integer networkId;

    /**
     * landing page
     */
    @Column(name = "landing_page_id")
    private Integer landingPageId;

    /**
     * 任务id
     */
    @Column(name = "offer_id")
    private Integer offerId;

    /**
     * 名字
     */
    private String name;

    /**
     * 访问链接
     */
    private String url;

    /**
     * 跳转链接
     */
    @Column(name = "redirect_url")
    private String redirectUrl;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 转化数量
     */
    private Integer leads;

    /**
     * 点击数
     */
    private Integer clicks;

    /**
     * 每点击花费
     */
    @Column(name = "cost_per_click")
    private BigDecimal costPerClick;

    /**
     * 平均每lead获利
     */
    @Column(name = "pay_per_lead")
    private BigDecimal payPerLead;

    /**
     * 是否激活
     */
    @Column(name = "is_active")
    private BigDecimal isActive;
}