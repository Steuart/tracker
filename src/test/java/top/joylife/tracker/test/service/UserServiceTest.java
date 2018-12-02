package top.joylife.tracker.test.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.joylife.tracker.common.bean.dto.UserDto;
import top.joylife.tracker.common.bean.param.UserParam;
import top.joylife.tracker.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void addUser(){
        UserParam user = new UserParam();
        user.setUsername("admin");
        user.setPassword("xxxxxxxxxxxx");
        user.setEmail("jianmo_o@163.com");
        user.setNickname("admin");
        user.setPhone("17364510725");
        Integer userId = userService.addUser(user);
        log.info("userId:{}",userId);
    }

    @Test
    public void getUserInfo(){
        String username = "admin";
        UserDto userDto = userService.getUserInfo(username);
        log.info("userDto:{}", JSON.toJSONString(userDto));
    }

    @Test
    public void updateUser(){
        UserDto user = userService.getUserInfo("admin");
        Integer userId = user.getId();
        UserParam userParam = new UserParam();
        userParam.setPassword("xcxcxcxc");
        userService.updateUser(userId,userParam);
    }

    @Test
    public void generatePassword(){
        String username = "admin";
        String password = "12345678";
        String result = userService.md5Password(username,password);
        log.info("result:{}",result);
    }
}
