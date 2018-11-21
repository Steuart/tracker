package top.joylife.tracker.dao.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "network")
@Data
public class Network extends BaseEntity {

    /**
     * 名字
     */
    private String name;

    /**
     * 主页
     */
    @Column(name = "home_page")
    private String homePage;

    /**
     * 回调url
     */
    @Column(name = "callback_url")
    private String callbackUrl;

    /**
     * 备注
     */
    private String remark;
}