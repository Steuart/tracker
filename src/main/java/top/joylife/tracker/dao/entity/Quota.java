package top.joylife.tracker.dao.entity;

import lombok.Data;
import lombok.Getter;

import java.util.Date;
import javax.persistence.*;

@Table(name = "quota")
@Data
public class Quota extends BaseEntity{

    /**
     * 类型，0-分组，1-指标
     */
    private Integer type;

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

    /**
     * 是否可删除，1-不可删除，0-可删除
     */
    @Column(name = "delete_able")
    private Integer deleteAble;

    /**
     * 类型
     */
    @Getter
    public enum TypeEnum {
        GROUP(0,"分组"),
        QOUTA(1,"指标");

        private Integer code;
        private String desc;

        TypeEnum(Integer code, String desc){
            this.code = code;
            this.desc = desc;
        }
    }

    /**
     * 是否可删除枚举值
     */
    @Getter
    public enum DeleteAbleEnum {
        YES(0,"可删除"),
        NO(1,"不可删除");

        private Integer code;
        private String desc;

        DeleteAbleEnum(Integer code, String desc){
            this.code =code;
            this.desc =desc;
        }
    }
}