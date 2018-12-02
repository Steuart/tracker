package top.joylife.tracker.common.bean.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 用户状态str
     */
    private String statusStr;

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

    /*
     * 创建时间
     */
    private Date dateCreate;

    /**
     * 创建时间字符串
     */
    private String dateCreateStr;

    /**
     * 更新时间
     */
    private Date dateUpdate;

    /**
     * 更新时间字符串
     */
    private String dateUpdateStr;
}
