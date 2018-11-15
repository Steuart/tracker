package top.joylife.tracker.service;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.joylife.tracker.cache.CampaignCache;
import top.joylife.tracker.common.bean.dto.CampaignDto;
import top.joylife.tracker.common.bean.param.CampaignParam;
import top.joylife.tracker.common.bean.query.CampaignPageQuery;
import top.joylife.tracker.common.util.PageUtil;
import top.joylife.tracker.dao.entity.Campaign;
import top.joylife.tracker.dao.impl.CampaignDao;

@Service
public class CampaignService {

    @Autowired
    private CampaignDao campaignDao;

    /**
     * 根据id查询项目
     * @param id
     * @return
     */
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

    /**
     * 保存项目
     * @param campaignParam
     * @return
     */
    public Integer saveCampaign(CampaignParam campaignParam){
        Campaign campaign = new Campaign();
        BeanUtils.copyProperties(campaignParam,campaign);
        campaignDao.insert(campaign);
        return campaign.getId();
    }

    /**
     * 更新项目
     * @param campaignParam
     */
    public void updateCampaign(CampaignParam campaignParam){
        Campaign campaign = new Campaign();
        BeanUtils.copyProperties(campaignParam,campaign);
        campaignDao.updateById(campaign);
    }

    /**
     * 分页获取项目列表
     * @param query
     * @return
     */
    public PageInfo<CampaignDto> pageCampaign(CampaignPageQuery query){
        PageInfo<Campaign> campaignPage = campaignDao.pageQuery(query);
        return PageUtil.copy(campaignPage,CampaignDto.class);
    }
}
