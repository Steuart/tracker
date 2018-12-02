package top.joylife.tracker.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import top.joylife.tracker.common.bean.dto.UserDto;
import top.joylife.tracker.common.bean.param.UserParam;
import top.joylife.tracker.common.bean.query.UserPageQuery;
import top.joylife.tracker.common.exception.Warning;
import top.joylife.tracker.common.util.MD5Util;
import top.joylife.tracker.common.util.PageUtil;
import top.joylife.tracker.dao.entity.User;
import top.joylife.tracker.dao.impl.UserDao;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
        UserDto userDto = null;
        if(user!=null){
            user.setPassword(null);
            userDto = new UserDto();
            BeanUtils.copyProperties(user,userDto);
        }
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
        if(user!=null){
            BeanUtils.copyProperties(user,userDto);
        }
        return userDto;
    }

    /**
     * 添加用户
     * @param userParam
     * @return
     */
    public Integer addUser(UserParam userParam){
        User user = new User();
        String username = userParam.getUsername();
        User oldUser = userDao.getByUsername(username);
        if(oldUser!=null){
            throw new Warning("该用户已经存在");
        }
        BeanUtils.copyProperties(userParam,user);
        if(!StringUtils.isEmpty(userParam.getPassword())){
            user.setPassword(md5Password(userParam.getUsername(),userParam.getPassword()));
        }
        try{
            userDao.insert(user);
        }catch (Exception e){
            if(e instanceof DuplicateKeyException){
                throw new Warning("用户名重复");
            }else {
                throw e;
            }
        }
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
        userParam.setUsername(null);
        BeanUtils.copyProperties(userParam,user);
        user.setId(userId);
        userDao.updateById(user);
    }

    /**
     * 删除用户
     * @param userId
     */
    public void deleteUser(Integer userId){
        userDao.softDeleteById(userId,User.class);
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
     * 根据id更新密码
     * @param username
     * @param oldPassword
     * @param password
     */
    public void updatePasswordById(String username,String oldPassword,String password){
        User user = userDao.getByUsername(username);
        oldPassword = md5Password(username,oldPassword);
        if(!oldPassword.equals(user.getPassword())){
            throw new Warning("原始密码错误");
        }
        updatePassword(user.getUsername(),password);
    }

    /**
     * 分页获取用户信息
     * @return
     */
    public PageInfo<UserDto> pageQueryUser(UserPageQuery query){
        PageInfo<User> pageInfo = userDao.pageQuery(query);
        PageInfo<UserDto> userDtoPageInfo = PageUtil.copy(pageInfo,UserDto.class);
        List<UserDto> userDtos = userDtoPageInfo.getList();
        if(!CollectionUtils.isEmpty(userDtos)){
            userDtos.forEach(userDto -> {
                User.StatusEnum statusEnum = User.StatusEnum.getByCode(userDto.getStatus());
                userDto.setStatusStr(statusEnum.getDesc());
            });
        }
        return userDtoPageInfo;
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
