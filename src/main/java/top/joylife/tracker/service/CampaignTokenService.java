package top.joylife.tracker.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.joylife.tracker.common.bean.dto.CampaignDto;
import top.joylife.tracker.common.bean.dto.CampaignTokenDto;
import top.joylife.tracker.dao.entity.CampaignToken;
import top.joylife.tracker.dao.impl.CampaignTokenDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignTokenService {

    @Autowired
    private CampaignTokenDao campaignTokenDao;

    /**
     * 根据campaignId查询
     * @param campaignId
     * @return
     */
    public List<CampaignTokenDto> listByCampaignId(Integer campaignId){
        List<CampaignToken> campaignTokens =  campaignTokenDao.listByCampaignId(campaignId);
        List<CampaignTokenDto> campaignTokenDtos = new ArrayList<>();
        if(!CollectionUtils.isEmpty(campaignTokens)){
            campaignTokens.forEach(campaignToken -> {
                CampaignTokenDto campaignTokenDto = new CampaignTokenDto();
                BeanUtils.copyProperties(campaignToken, campaignTokenDto);
                campaignTokenDtos.add(campaignTokenDto);
            });
        }
        return campaignTokenDtos;
    }

}
