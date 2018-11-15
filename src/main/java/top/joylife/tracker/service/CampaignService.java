package top.joylife.tracker.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.joylife.tracker.cache.CampaignCache;
import top.joylife.tracker.common.bean.dto.CampaignDto;
import top.joylife.tracker.common.bean.param.CampaignParam;
import top.joylife.tracker.common.bean.query.CampaignPageQuery;
import top.joylife.tracker.common.util.PageUtil;
import top.joylife.tracker.dao.entity.Campaign;
import top.joylife.tracker.dao.impl.CampaignDao;

import java.util.ArrayList;
import java.util.List;

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
        if(campaign==null){
            return null;
        }
        campaignDto = new CampaignDto();
        BeanUtils.copyProperties(campaign,campaignDto);
        CampaignCache.saveCache(campaignDto);
        return campaignDto;
    }

    public Integer saveCampaign(CampaignParam campaignParam){
        Campaign campaign = new Campaign();
        BeanUtils.copyProperties(campaignParam,campaign);
        campaignDao.insert(campaign);
        return campaign.getId();
    }

    public void updateCampaign(CampaignParam campaignParam){
        Campaign campaign = new Campaign();
        BeanUtils.copyProperties(campaignParam,campaign);
        campaignDao.updateById(campaign);
    }

    public PageInfo<CampaignDto> pageCampaign(CampaignPageQuery query){
        PageInfo<Campaign> campaignPage = campaignDao.pageCampaign(query);
        return PageUtil.copy(campaignPage,CampaignDto.class);
    }
}
