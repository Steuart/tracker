package top.joylife.tracker.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "system_config")
@Data
public class SystemConfig extends BaseEntity{

    /**
     * 名字
     */
    private String name;

    /**
     * 值
     */
    private String value;

}