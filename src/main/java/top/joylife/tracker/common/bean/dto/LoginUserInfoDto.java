package top.joylife.tracker.common.bean.dto;

import lombok.Data;

@Data
public class LoginUserInfoDto {

    private String token;

    private UserDto user;
}
