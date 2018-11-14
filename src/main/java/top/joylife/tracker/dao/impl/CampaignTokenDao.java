package top.joylife.tracker.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import top.joylife.tracker.dao.MyMapper;
import top.joylife.tracker.dao.entity.Campaign;
import top.joylife.tracker.dao.mapper.CampaignMapper;

@Repository
public class CampaignTokenDao extends BaseDao<Campaign> {

    @Autowired
    private CampaignMapper campaignMapper;

    @Override
    public MyMapper<Campaign> getMapper() {
        return campaignMapper;
    }
}
