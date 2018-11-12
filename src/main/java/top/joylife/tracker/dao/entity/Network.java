package top.joylife.tracker.dao.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "network")
public class Network {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 名字
     */
    private String name;

    /**
     * 域名
     */
    private String domain;

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
     * 获取域名
     *
     * @return domain - 域名
     */
    public String getDomain() {
        return domain;
    }

    /**
     * 设置域名
     *
     * @param domain 域名
     */
    public void setDomain(String domain) {
        this.domain = domain;
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
     * @return date_delete
     */
    public Date getDateDelete() {
        return dateDelete;
    }

    /**
     * @param dateDelete
     */
    public void setDateDelete(Date dateDelete) {
        this.dateDelete = dateDelete;
    }
}