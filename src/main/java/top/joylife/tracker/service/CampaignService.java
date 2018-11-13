package top.joylife.tracker.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.joylife.tracker.cache.CampaignCache;
import top.joylife.tracker.common.dto.CampaignDto;
import top.joylife.tracker.dao.entity.Campaign;
import top.joylife.tracker.dao.impl.CampaignDao;

@Service
public class CampaignService {

    @Autowired
    private CampaignDao campaignDao;

    public CampaignDto getCampaign(Integer id){
        CampaignDto campaignDto = CampaignCache.getFromCache(id);
        if(campaignDto!=null){
            return campaignDto;
        }
        Campaign campaign = campaignDao.getById(id);
        campaignDto = new CampaignDto();
        BeanUtils.copyProperties(campaign,campaignDto);
        CampaignCache.saveCache(campaignDto);
        return campaignDto;
    }
}
