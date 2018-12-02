package top.joylife.tracker.dao.entity;

import com.alibaba.druid.support.spring.stat.annotation.Stat;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Table(name = "user")
@Data
public class User extends BaseEntity{

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像url
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户状态枚举值
     */
    @Getter
    public enum StatusEnum {
        ACTIVE(0,"活动"),
        DISABLE(1,"禁用"),
        DO_NOT_KNOW(99,"未知");

        private Integer code;
        private String desc;

        StatusEnum(Integer code, String desc){
            this.code = code;
            this.desc = desc;
        }

        public static StatusEnum getByCode(Integer code) {
            for(StatusEnum enums: StatusEnum.values()){
                if(enums.code.equals(code)){
                    return enums;
                }
            }
            return StatusEnum.DO_NOT_KNOW;
        }
    }
}