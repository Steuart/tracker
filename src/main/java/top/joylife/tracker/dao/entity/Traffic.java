package top.joylife.tracker.dao.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "traffic")
@Data
public class Traffic extends BaseEntity{

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
}