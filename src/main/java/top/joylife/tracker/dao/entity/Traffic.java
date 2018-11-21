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
     * http请求方法
     */
    @Column(name = "home_page")
    private String homePage;


    /**
     * 备注
     */
    private String remark;
}