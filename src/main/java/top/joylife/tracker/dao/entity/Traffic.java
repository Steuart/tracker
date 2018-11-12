package top.joylife.tracker.dao.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "traffic")
public class Traffic {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 流量名字
     */
    private String name;

    /**
     * 是否使用token
     */
    @Column(name = "use_token")
    private Integer useToken;

    /**
     * http请求方法
     */
    @Column(name = "http_method")
    private String httpMethod;

    /**
     * 回调url
     */
    @Column(name = "postback_url")
    private String postbackUrl;

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
     * 获取流量名字
     *
     * @return name - 流量名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置流量名字
     *
     * @param name 流量名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取是否使用token
     *
     * @return use_token - 是否使用token
     */
    public Integer getUseToken() {
        return useToken;
    }

    /**
     * 设置是否使用token
     *
     * @param useToken 是否使用token
     */
    public void setUseToken(Integer useToken) {
        this.useToken = useToken;
    }

    /**
     * 获取http请求方法
     *
     * @return http_method - http请求方法
     */
    public String getHttpMethod() {
        return httpMethod;
    }

    /**
     * 设置http请求方法
     *
     * @param httpMethod http请求方法
     */
    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    /**
     * 获取回调url
     *
     * @return postback_url - 回调url
     */
    public String getPostbackUrl() {
        return postbackUrl;
    }

    /**
     * 设置回调url
     *
     * @param postbackUrl 回调url
     */
    public void setPostbackUrl(String postbackUrl) {
        this.postbackUrl = postbackUrl;
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