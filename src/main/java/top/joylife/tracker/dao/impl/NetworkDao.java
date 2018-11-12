package top.joylife.tracker.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import top.joylife.tracker.dao.entity.Network;
import top.joylife.tracker.dao.MyMapper;
import top.joylife.tracker.dao.mapper.NetworkMapper;

@Repository
public class NetworkDao extends BaseDao<Network>{

    @Autowired
    private NetworkMapper networkMapper;

    @Override
    public MyMapper<Network> getMapper() {
        return networkMapper;
    }
}
