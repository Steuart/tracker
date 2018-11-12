package top.joylife.tracker.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.joylife.tracker.common.dto.CampaignDto;
import top.joylife.tracker.dao.entity.Campaign;
import top.joylife.tracker.dao.impl.CampaignDao;

@Service
public class CampaignService {

    @Autowired
    private CampaignDao campaignDao;

    public CampaignDto getCampaign(Integer id){
        Campaign campaign = campaignDao.getById(id);
        CampaignDto campaignDto = new CampaignDto();
        BeanUtils.copyProperties(campaign,campaignDto);
        return campaignDto;
    }
}
