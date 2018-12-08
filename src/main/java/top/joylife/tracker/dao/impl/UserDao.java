package top.joylife.tracker.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import top.joylife.tracker.common.bean.query.BasePageQuery;
import top.joylife.tracker.common.bean.query.UserPageQuery;
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

    /**
     * 构建分页查询条件
     *
     * @param pageQuery
     * @return
     */
    @Override
    public Example buildPageQueryExample(BasePageQuery pageQuery) {
        UserPageQuery query = (UserPageQuery)pageQuery;
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(query.getUsername())){
            criteria.andLike("username","%"+query.getUsername()+"%");
        }
        criteria.andBetween("dateCreate",query.getBeginDate(),query.getEndDate());
        return example;
    }

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    public User getByUsername(String username){
        if(StringUtils.isEmpty(username)){
            return null;
        }
        Example example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("username",username)
                .andEqualTo("dateDelete",0);
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
