package top.joylife.tracker.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "traffic_token")
@Data
public class TrafficToken extends BaseEntity{

    /**
     * 流量Id
     */
    @Column(name = "traffic_id")
    private Integer trafficId;

    /**
     * 名字
     */
    private String name;

    /**
     * 参数值
     */
    private String value;

    /**
     * 备注
     */
    private String remark;
}