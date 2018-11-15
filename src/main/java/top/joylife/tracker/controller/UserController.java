package top.joylife.tracker.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.joylife.tracker.cache.AuthHolder;
import top.joylife.tracker.common.ErrorCode;
import top.joylife.tracker.common.ReData;
import top.joylife.tracker.common.bean.dto.UserDto;
import top.joylife.tracker.common.exception.Warning;
import top.joylife.tracker.common.bean.param.UserParam;
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

    /**
     * 添加用户
     * @param userParam
     * @return
     */
    @PutMapping
    public ReData<Integer> addUser(@RequestBody UserParam userParam){
        String password = userParam.getPassword();
        if(StringUtils.isEmpty(password)){
            throw new Warning("密码不能为空");
        }

        String username = userParam.getUsername();
        if(StringUtils.isEmpty(username)){
            throw new Warning("用户名不能为空");
        }
        Integer userId = userService.addUser(userParam);
        return ReUtil.success(userId);
    }

    /**
     * 更新用户
     * @param id
     * @param userParam
     * @return
     */
    @PostMapping(value = "{id}")
    public ReData<Integer> updateUser(@PathVariable Integer id, @RequestBody UserParam userParam){
        if(id==null){
            throw new Warning("用户id不能为空");
        }
        userService.updateUser(id,userParam);
        return ReUtil.success(id);
    }

    /**
     * 更新用户密码
     * @param username
     * @param password
     * @return
     */
    @PostMapping(value = "password/{username}")
    public ReData<String> updatePassword(@PathVariable String username,String password){
        if(StringUtils.isEmpty(username)){
            throw new Warning("用户名不能为空");
        }
        if(StringUtils.isEmpty(password)){
            throw new Warning("密码不能为空");
        }
        userService.updatePassword(username,password);
        return ReUtil.success(username);
    }

}
