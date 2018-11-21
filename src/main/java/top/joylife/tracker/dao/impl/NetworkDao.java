package top.joylife.tracker.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;
import top.joylife.tracker.common.bean.query.BasePageQuery;
import top.joylife.tracker.common.bean.query.NetworkPageQuery;
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

    /**
     * 构建分页查询条件
     *
     * @param pageQuery
     * @return
     */
    @Override
    public Example buildPageQueryExample(BasePageQuery pageQuery) {
        NetworkPageQuery query = (NetworkPageQuery)pageQuery;
        Example example = new Example(Network.class);
        example.createCriteria()
                .andLike("name",query.getName())
                .andBetween("dateCreate",query.getBeginDate(),query.getEndDate());
        return example;
    }
}
