package top.joylife.tracker.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;
import top.joylife.tracker.common.bean.query.BasePageQuery;
import top.joylife.tracker.common.bean.query.TrafficPageQuery;
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

    /**
     * 构建分页查询条件
     *
     * @param pageQuery
     * @return
     */
    @Override
    public Example buildPageQueryExample(BasePageQuery pageQuery) {
        TrafficPageQuery query = (TrafficPageQuery)pageQuery;
        Example example = new Example(Traffic.class);
        example.createCriteria()
                .andLike("name",query.getName())
                .andBetween("dateCreate",query.getBeginDate(),query.getEndDate());
        return example;
    }
}
