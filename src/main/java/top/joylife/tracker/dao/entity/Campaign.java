package top.joylife.tracker.dao.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "campaign")
public class Campaign {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

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
     * 重定向链接
     */
    @Column(name = "redirect_link")
    private String redirectLink;

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
    private Integer costPerClick;

    /**
     * 平均每lead获利
     */
    @Column(name = "pay_per_lead")
    private Integer payPerLead;

    /**
     * 是否激活
     */
    @Column(name = "is_active")
    private Integer isActive;

    /**
     * 创建时间
     */
    @Column(name = "date_create")
    private Date dateCreate;

    /**
     * 更新时间
     */
    @Column(name = "date_update")
    private Date dateUpdate;

    /**
     * 删除时间
     */
    @Column(name = "date_delete")
    private Date dateDelete;

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
     * 获取流量id
     *
     * @return traffic_id - 流量id
     */
    public Integer getTrafficId() {
        return trafficId;
    }

    /**
     * 设置流量id
     *
     * @param trafficId 流量id
     */
    public void setTrafficId(Integer trafficId) {
        this.trafficId = trafficId;
    }

    /**
     * 获取联盟id
     *
     * @return network_id - 联盟id
     */
    public Integer getNetworkId() {
        return networkId;
    }

    /**
     * 设置联盟id
     *
     * @param networkId 联盟id
     */
    public void setNetworkId(Integer networkId) {
        this.networkId = networkId;
    }

    /**
     * 获取landing page
     *
     * @return landing_page_id - landing page
     */
    public Integer getLandingPageId() {
        return landingPageId;
    }

    /**
     * 设置landing page
     *
     * @param landingPageId landing page
     */
    public void setLandingPageId(Integer landingPageId) {
        this.landingPageId = landingPageId;
    }

    /**
     * 获取任务id
     *
     * @return offer_id - 任务id
     */
    public Integer getOfferId() {
        return offerId;
    }

    /**
     * 设置任务id
     *
     * @param offerId 任务id
     */
    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    /**
     * 获取名字
     *
     * @return name - 名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名字
     *
     * @param name 名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取重定向链接
     *
     * @return redirect_link - 重定向链接
     */
    public String getRedirectLink() {
        return redirectLink;
    }

    /**
     * 设置重定向链接
     *
     * @param redirectLink 重定向链接
     */
    public void setRedirectLink(String redirectLink) {
        this.redirectLink = redirectLink;
    }

    /**
     * 获取类型
     *
     * @return type - 类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取转化数量
     *
     * @return leads - 转化数量
     */
    public Integer getLeads() {
        return leads;
    }

    /**
     * 设置转化数量
     *
     * @param leads 转化数量
     */
    public void setLeads(Integer leads) {
        this.leads = leads;
    }

    /**
     * 获取点击数
     *
     * @return clicks - 点击数
     */
    public Integer getClicks() {
        return clicks;
    }

    /**
     * 设置点击数
     *
     * @param clicks 点击数
     */
    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    /**
     * 获取每点击花费
     *
     * @return cost_per_click - 每点击花费
     */
    public Integer getCostPerClick() {
        return costPerClick;
    }

    /**
     * 设置每点击花费
     *
     * @param costPerClick 每点击花费
     */
    public void setCostPerClick(Integer costPerClick) {
        this.costPerClick = costPerClick;
    }

    /**
     * 获取平均每lead获利
     *
     * @return pay_per_lead - 平均每lead获利
     */
    public Integer getPayPerLead() {
        return payPerLead;
    }

    /**
     * 设置平均每lead获利
     *
     * @param payPerLead 平均每lead获利
     */
    public void setPayPerLead(Integer payPerLead) {
        this.payPerLead = payPerLead;
    }

    /**
     * 获取是否激活
     *
     * @return is_active - 是否激活
     */
    public Integer getIsActive() {
        return isActive;
    }

    /**
     * 设置是否激活
     *
     * @param isActive 是否激活
     */
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    /**
     * 获取创建时间
     *
     * @return date_create - 创建时间
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     * 设置创建时间
     *
     * @param dateCreate 创建时间
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    /**
     * 获取更新时间
     *
     * @return date_update - 更新时间
     */
    public Date getDateUpdate() {
        return dateUpdate;
    }

    /**
     * 设置更新时间
     *
     * @param dateUpdate 更新时间
     */
    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    /**
     * 获取删除时间
     *
     * @return date_delete - 删除时间
     */
    public Date getDateDelete() {
        return dateDelete;
    }

    /**
     * 设置删除时间
     *
     * @param dateDelete 删除时间
     */
    public void setDateDelete(Date dateDelete) {
        this.dateDelete = dateDelete;
    }
}