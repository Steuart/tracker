package top.joylife.tracker.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.joylife.tracker.authorize.AuthHolder;
import top.joylife.tracker.common.ErrorCode;
import top.joylife.tracker.common.ReData;
import top.joylife.tracker.common.dto.UserDto;
import top.joylife.tracker.common.exception.Warning;
import top.joylife.tracker.common.util.ReUtil;
import top.joylife.tracker.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param username
     * @param password
     * @param response
     * @return
     */
    @PostMapping(value = "login")
    public ReData<UserDto> login(String username, String password, HttpServletResponse response){
        UserDto userDto = userService.getUserInfo(username);
        if(userDto==null){
            throw new Warning(ErrorCode.username_wrong);
        }
        if(StringUtils.isEmpty(password)){
            throw new Warning(ErrorCode.password_can_not_be_empty);
        }
        String dbPassword = userDto.getPassword();
        String md5Password = userService.md5Password(username,password);
        if(!dbPassword.endsWith(md5Password)){
            throw new Warning(ErrorCode.username_wrong);
        }
        UserDto cacheUser = new UserDto();
        BeanUtils.copyProperties(userDto,cacheUser);
        String token = AuthHolder.cacheUserInfo(cacheUser);
        Cookie cookie = new Cookie(AuthHolder.TOKEN, token);
        cookie.setMaxAge(AuthHolder.MAX_AGE);
        cookie.setPath("/");
        response.addCookie(cookie);
        userDto.setPassword(null);
        return ReUtil.success(userDto);
    }



}
