package top.joylife.tracker.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import top.joylife.tracker.dao.MyMapper;
import top.joylife.tracker.dao.entity.User;
import top.joylife.tracker.dao.mapper.UserMapper;

@Repository
public class UserDao extends BaseDao<User>{

    @Autowired
    private UserMapper userMapper;

    @Override
    public MyMapper<User> getMapper() {
        return userMapper;
    }

    public User getByUsername(String username){
        if(StringUtils.isEmpty(username)){
            return null;
        }
        Example example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("username",username);
        return userMapper.selectOneByExample(example);
    }

    public void updatePassword(String username,String password){
        Example example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("username",username);
        User user = new User();
        user.setPassword(password);
        userMapper.updateByExampleSelective(user,example);
    }
}
