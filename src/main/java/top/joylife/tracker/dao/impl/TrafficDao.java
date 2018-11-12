package top.joylife.tracker.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import top.joylife.tracker.dao.entity.Traffic;
import top.joylife.tracker.dao.MyMapper;
import top.joylife.tracker.dao.mapper.TrafficMapper;

@Repository
public class TrafficDao extends BaseDao<Traffic>{

    @Autowired
    private TrafficMapper trafficMapper;

    @Override
    public MyMapper<Traffic> getMapper() {
        return trafficMapper;
    }
}
