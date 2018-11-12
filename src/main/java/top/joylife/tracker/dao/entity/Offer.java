package top.joylife.tracker.dao.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 联盟id
     */
    @Column(name = "network_id")
    private Integer networkId;

    /**
     * 名字
     */
    private String name;

    /**
     * url
     */
    private String url;

    /**
     * 支付金额
     */
    private Integer payout;

    @Column(name = "date_create")
    private Date dateCreate;

    @Column(name = "date_update")
    private Date dateUpdate;

    /**
     * 0
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
     * 获取url
     *
     * @return url - url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置url
     *
     * @param url url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取支付金额
     *
     * @return payout - 支付金额
     */
    public Integer getPayout() {
        return payout;
    }

    /**
     * 设置支付金额
     *
     * @param payout 支付金额
     */
    public void setPayout(Integer payout) {
        this.payout = payout;
    }

    /**
     * @return date_create
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     * @param dateCreate
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    /**
     * @return date_update
     */
    public Date getDateUpdate() {
        return dateUpdate;
    }

    /**
     * @param dateUpdate
     */
    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    /**
     * 获取0
     *
     * @return date_delete - 0
     */
    public Date getDateDelete() {
        return dateDelete;
    }

    /**
     * 设置0
     *
     * @param dateDelete 0
     */
    public void setDateDelete(Date dateDelete) {
        this.dateDelete = dateDelete;
    }
}