package top.joylife.tracker.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;
import top.joylife.tracker.common.bean.query.BasePageQuery;
import top.joylife.tracker.common.bean.query.CampaignPageQuery;
import top.joylife.tracker.dao.entity.Campaign;
import top.joylife.tracker.dao.mapper.CampaignMapper;
import top.joylife.tracker.dao.MyMapper;


@Repository
public class CampaignDao extends BaseDao<Campaign>{

    @Autowired
    private CampaignMapper campaignMapper;

    @Override
    public MyMapper<Campaign> getMapper() {
        return campaignMapper;
    }

    /**
     * 构建分页查询条件
     *
     * @param pageQuery
     * @return
     */
    @Override
    public Example buildPageQueryExample(BasePageQuery pageQuery) {
        Example example = new Example(Campaign.class);
        CampaignPageQuery query = (CampaignPageQuery) pageQuery;

        return example;
    }
}
