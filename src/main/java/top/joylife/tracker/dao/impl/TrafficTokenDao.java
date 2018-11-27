package top.joylife.tracker.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;
import top.joylife.tracker.common.bean.query.BasePageQuery;
import top.joylife.tracker.dao.entity.TrafficToken;
import top.joylife.tracker.dao.MyMapper;
import top.joylife.tracker.dao.mapper.TrafficTokenMapper;

import java.util.List;

@Repository
public class TrafficTokenDao extends BaseDao<TrafficToken>{

    @Autowired
    private TrafficTokenMapper trafficTokenMapper;

    @Override
    public MyMapper<TrafficToken> getMapper() {
        return trafficTokenMapper;
    }

    /**
     * 构建分页查询条件
     *
     * @param pageQuery
     * @return
     */
    @Override
    public Example buildPageQueryExample(BasePageQuery pageQuery) {
        return null;
    }


    /**
     * 根据trafficId查询trafficToken
     * @param trafficId
     * @return
     */
    public List<TrafficToken> listByTrafficId(Integer trafficId){
        Example example = new Example(TrafficToken.class);
        example.createCriteria()
                .andEqualTo("trafficId",trafficId);
        example.orderBy("id").asc();
        return trafficTokenMapper.selectByExample(example);
    }

    /**
     * 根据名字查询
     * @param name
     * @return
     */
    public List<TrafficToken> selectByName(String name){
        Example example = new Example(TrafficToken.class);
        example.createCriteria()
                .andEqualTo("name", name);
        return trafficTokenMapper.selectByExample(example);
    }
}
