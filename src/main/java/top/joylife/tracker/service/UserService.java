package top.joylife.tracker.service;

import com.github.pagehelper.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.joylife.tracker.common.dto.UserDto;
import top.joylife.tracker.common.param.UserParam;
import top.joylife.tracker.common.util.MD5Util;
import top.joylife.tracker.dao.entity.User;
import top.joylife.tracker.dao.impl.UserDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    private static final String CONFUSION="PwihB2hi";

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    public UserDto getUserInfo(String username){
        User user = userDao.getByUsername(username);
        user.setPassword(null);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    public UserDto getUserInfoWithPassword(String username){
        User user = userDao.getByUsername(username);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }

    /**
     * 添加用户
     * @param userParam
     * @return
     */
    public Integer addUser(UserParam userParam){
        User user = new User();
        BeanUtils.copyProperties(userParam,user);
        if(!StringUtils.isEmpty(userParam.getPassword())){
            user.setPassword(md5Password(userParam.getUsername(),userParam.getPassword()));
        }
        userDao.insert(user);
        return user.getId();
    }

    /**
     * 更新用户
     * @param userId
     * @param userParam
     */
    public void updateUser(Integer userId,UserParam userParam){
        User user = new User();
        userParam.setPassword(null);
        BeanUtils.copyProperties(userParam,user);
        user.setId(userId);
        if(!StringUtils.isEmpty(userParam.getPassword())){
            user.setPassword(md5Password(userParam.getUsername(),userParam.getPassword()));
        }
        userDao.updateById(user);
    }

    /**
     * 更新用户密码
     * @param username
     * @param password
     */
    public void updatePassword(String username,String password){
        userDao.updatePassword(username,md5Password(username,password));
    }

    /**
     * 分页获取用户信息
     * @return
     */
    public Page<UserDto> getUserInfoPage(){
        List<UserDto> user = new ArrayList<>();
        return new Page<>();
    }

    /**
     * 生成密码
     * @param username
     * @param originPassword
     * @return
     */
    public String md5Password(String username,String originPassword){
        return MD5Util.getMD5Str(username+CONFUSION+originPassword);
    }
}
