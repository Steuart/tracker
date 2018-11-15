package top.joylife.tracker.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;
import top.joylife.tracker.common.bean.query.BasePageQuery;
import top.joylife.tracker.dao.entity.TrafficToken;
import top.joylife.tracker.dao.MyMapper;
import top.joylife.tracker.dao.mapper.TrafficTokenMapper;

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
}
