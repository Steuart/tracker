package top.joylife.tracker.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
}
