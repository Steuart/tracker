package top.joylife.tracker.common.bean.dto;

import lombok.Data;

@Data
public class UpdateUserPasswordDto {
    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;
}
