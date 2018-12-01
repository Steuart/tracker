package top.joylife.tracker.service;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.joylife.tracker.cache.CampaignCache;
import top.joylife.tracker.common.bean.dto.CampaignDto;
import top.joylife.tracker.common.bean.param.CampaignParam;
import top.joylife.tracker.common.bean.param.TokensParam;
import top.joylife.tracker.common.bean.query.CampaignPageQuery;
import top.joylife.tracker.common.enums.SystemConfigEnum;
import top.joylife.tracker.common.exception.Warning;
import top.joylife.tracker.common.util.PageUtil;
import top.joylife.tracker.dao.entity.*;
import top.joylife.tracker.dao.impl.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignService {

    @Autowired
    private CampaignDao campaignDao;

    @Autowired
    private OfferDao offerDao;

    @Autowired
    private SystemConfigDao systemConfigDao;

    @Autowired
    private TokensDao tokensDao;
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
        Campaign campaign = generateCampaign(campaignParam);
        campaignDao.insert(campaign);
        //保存campaignToken
        Integer campaignId = campaign.getId();
        List<Tokens> tokens =  generateCampaignToken(campaignId, campaignParam.getTokens());
        tokensDao.batchAddTokens(tokens);
        return campaignId;
    }

    /**
     * 更新项目
     * @param campaignParam
     */
    public void updateCampaign(Integer id,CampaignParam campaignParam){
        Campaign campaign = generateCampaign(campaignParam);
        campaign.setId(id);
        campaignDao.updateById(campaign);
        //保存campaignToken
        Integer campaignId = campaign.getId();
        List<Tokens> tokens = generateCampaignToken(campaignId, campaignParam.getTokens());
        tokensDao.deleteByIdRefAndType(campaignId,Tokens.TypeEnum.CAMPAIGN.getCode());
        tokensDao.batchAddTokens(tokens);
    }

    /**
     * 删除项目
     * @param id
     */
    public void deleteCampaign(Integer id){
        campaignDao.softDeleteById(id,Campaign.class);
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

    /**
     * 生成跳转链接
     * @return
     */
    private String generateRedirectLink(List<TokensParam> tokens){
        SystemConfig systemConfig = systemConfigDao.getByName(SystemConfigEnum.DOMAIN.getName());
        String domain = systemConfig.getValue();
        StringBuilder str = new StringBuilder();
        str.append(domain).append("?");
        tokens.forEach(param -> {
            str.append(param.getName()).append("=").append(param.getValue()).append("&");
        });
        return str.substring(0,str.length()-1);
    }

    /**
     * 生成campaign
     * @param campaignParam
     * @return
     */
    private Campaign generateCampaign(CampaignParam campaignParam){
        Campaign campaign = new Campaign();
        Integer offerId = campaignParam.getOfferId();
        if(offerId == null){
            throw new Warning("OfferId不能为空");
        }
        BeanUtils.copyProperties(campaignParam,campaign);

        //查询networkId
        Offer offer = offerDao.getById(offerId);
        if(offer == null){
            throw new Warning("查询不到该Offer");
        }
        Integer networkId = offer.getNetworkId();
        campaign.setNetworkId(networkId);

        //生成redirectLink
        String redirectLink = generateRedirectLink(campaignParam.getTokens());
        campaign.setRedirectLink(redirectLink);
        return campaign;
    }

    /**
     * 生成campaignToken
     * @param param
     * @return
     */
    private List<Tokens> generateCampaignToken(Integer campaignId, List<TokensParam> param){
        List<Tokens> tokens = new ArrayList<>();
        param.forEach(tokenParam ->{
            Tokens token = new Tokens();
            BeanUtils.copyProperties(tokenParam, token);
            token.setIdRef(campaignId);
            token.setType(Tokens.TypeEnum.CAMPAIGN.getCode());
            tokens.add(token);
        });
        return tokens;
    }
}
