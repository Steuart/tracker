package top.joylife.tracker.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.joylife.tracker.cache.AuthHolder;
import top.joylife.tracker.common.ErrorCode;
import top.joylife.tracker.common.ReData;
import top.joylife.tracker.common.bean.dto.LoginUserInfoDto;
import top.joylife.tracker.common.bean.dto.UpdateUserPasswordDto;
import top.joylife.tracker.common.bean.dto.UserDto;
import top.joylife.tracker.common.bean.query.UserPageQuery;
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
    @PostMapping(value = "/login")
    public ReData<LoginUserInfoDto> login(String username, String password, HttpServletResponse response){
        LoginUserInfoDto result = new LoginUserInfoDto();
        UserDto userDto = userService.getUserInfoWithPassword(username);
        if(userDto==null){
            throw new Warning(ErrorCode.username_wrong);
        }
        if(StringUtils.isEmpty(password)){
            throw new Warning(ErrorCode.password_can_not_be_empty);
        }
        String dbPassword = userDto.getPassword();
        String md5Password = userService.md5Password(username,password);
        if(dbPassword == null || !dbPassword.equals(md5Password)){
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
        result.setToken(token);
        result.setUser(userDto);
        return ReUtil.success(result);
    }

    /**
     * 分页获取用户信息
     * @param query
     * @return
     */
    @PostMapping(value = "/page")
    public ReData<PageInfo<UserDto>> pageUser(@RequestBody(required = false) UserPageQuery query){
        if(query == null){
            query = new UserPageQuery();
        }
        PageInfo<UserDto> userDtoPageInfo = userService.pageQueryUser(query);
        return ReUtil.success(userDtoPageInfo);
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
    @PostMapping(value = "/{id}")
    public ReData<Integer> updateUser(@PathVariable Integer id, @RequestBody(required = false) UserParam userParam){
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
    @PostMapping(value = "/password/{username}")
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

    /**
     * 更新用户密码，通过用户id更新
     * @param username
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PostMapping(value = "/passwordWithOld/{username}")
    public ReData<String> updatePasswordById(@PathVariable String username, @RequestBody UpdateUserPasswordDto passwordDto){
        if(StringUtils.isEmpty(username)){
            throw new Warning("用户id不能为空");
        }
        if(StringUtils.isEmpty(passwordDto.getOldPassword())){
            throw new Warning("旧密码不能为空");
        }
        userService.updatePasswordById(username,passwordDto.getOldPassword(),passwordDto.getNewPassword());
        return ReUtil.success("success");
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ReData<Integer> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ReUtil.success(id);
    }


}
