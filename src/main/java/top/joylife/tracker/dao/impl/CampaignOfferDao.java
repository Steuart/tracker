package top.joylife.tracker.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import top.joylife.tracker.dao.entity.CampaignOffer;
import top.joylife.tracker.dao.mapper.CampaignOfferMapper;
import top.joylife.tracker.dao.MyMapper;

@Repository
public class CampaignOfferDao extends BaseDao<CampaignOffer>{

    @Autowired
    private CampaignOfferMapper campaignOfferMapper;

    @Override
    public MyMapper<CampaignOffer> getMapper() {
        return campaignOfferMapper;
    }
}
