package top.joylife.tracker.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import top.joylife.tracker.dao.entity.CampaignLandingPage;
import top.joylife.tracker.dao.mapper.CampaignLandingPageMapper;
import top.joylife.tracker.dao.MyMapper;

@Repository
public class CampaignLandingPageDao extends BaseDao<CampaignLandingPage>{

    @Autowired
    private CampaignLandingPageMapper campaignLandingPageMapper;

    @Override
    public MyMapper<CampaignLandingPage> getMapper() {
        return campaignLandingPageMapper;
    }
}
