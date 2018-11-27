package top.joylife.tracker.dao.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Table(name = "quota")
@Data
public class Quota extends BaseEntity{

    /**
     * 指标code
     */
    private String code;

    /**
     * 指标名字
     */
    private String name;

    /**
     * 指标备注
     */
    private String remark;

    /**
     * 分组id
     */
    @Column(name = "group_id")
    private Integer groupId;
}