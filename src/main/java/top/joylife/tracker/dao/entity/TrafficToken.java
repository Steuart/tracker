package top.joylife.tracker.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "traffic_token")
@Data
public class TrafficToken extends BaseEntity{

    private String name;

    /**
     * 参数名
     */
    private String param;

    /**
     * 参数值
     */
    private String value;
}