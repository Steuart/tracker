package top.joylife.tracker.dao.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Table(name = "tokens")
@Data
public class Tokens extends BaseEntity{

    /**
     * 业务id
     */
    @Column(name = "id_ref")
    private Integer idRef;

    /**
     * 类型，0-campaign,1-traffic,2-network
     */
    private Integer type;

    /**
     * 名字
     */
    private String name;

    /**
     * 值
     */
    private String value;

    @Getter
    public enum TypeEnum {
        CAMPAIGN(0,"campaign"),
        TRAFFIC(1,"traffic"),
        NETWORK(2,"network"),
        DEFAULT(-1,"default");

        private Integer code;
        private String desc;

        TypeEnum(Integer code, String desc){
            this.code = code;
            this.desc = desc;
        }

        /**
         * 根据code获取枚举值
         * @param code
         * @return
         */
        public static TypeEnum getByCode(Integer code){
            for (TypeEnum typeEnum:TypeEnum.values()){
                if(typeEnum.getCode().equals(code)){
                    return typeEnum;
                }
            }
            return TypeEnum.DEFAULT;
        }
    }
}