package top.joylife.tracker.service;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import top.joylife.tracker.cache.CampaignCache;
import top.joylife.tracker.common.bean.dto.CampaignDto;
import top.joylife.tracker.common.bean.param.CampaignParam;
import top.joylife.tracker.common.bean.param.TokensParam;
import top.joylife.tracker.common.bean.query.CampaignPageQuery;
import top.joylife.tracker.common.enums.SystemConfigEnum;
import top.joylife.tracker.common.exception.Warning;
import top.joylife.tracker.common.util.BeanUtil;
import top.joylife.tracker.dao.entity.*;
import top.joylife.tracker.dao.impl.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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

    @Autowired
    private TokensService tokensService;

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
        //生成访问链接
        String url = generateUrl(campaign.getId(),campaignParam.getTokens());
        campaign.setUrl(url);
        campaignDao.updateById(campaign);
        //保存campaignToken
        Integer campaignId = campaign.getId();
        List<TokensParam> tokensParams = campaignParam.getTokens();
        if(!CollectionUtils.isEmpty(tokensParams)){
            tokensService.batchAddTokens(tokensParams,campaignId,Tokens.TypeEnum.CAMPAIGN);
        }
        return campaignId;
    }

    /**
     * 更新项目
     * @param campaignParam
     */
    public void updateCampaign(Integer id,CampaignParam campaignParam){
        Campaign campaign = generateCampaign(campaignParam);
        campaign.setId(id);
        //生成访问链接
        String url = generateUrl(id,campaignParam.getTokens());
        campaign.setUrl(url);
        campaignDao.updateById(campaign);
        //保存campaignToken
        Integer campaignId = campaign.getId();
        List<TokensParam> tokensParams = campaignParam.getTokens();
        if(!CollectionUtils.isEmpty(tokensParams)){
            tokensService.batchUpdateTokens(tokensParams,campaignId,Tokens.TypeEnum.CAMPAIGN);
        }
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
        PageInfo<CampaignDto> campaignDtoPageInfo = BeanUtil.copy(campaignPage,CampaignDto.class);
        List<CampaignDto> campaignDtos = campaignDtoPageInfo.getList();
        DecimalFormat df = new DecimalFormat("0.00");
        BigDecimal handren = new BigDecimal(100);
        if(!CollectionUtils.isEmpty(campaignDtos)){
            campaignDtos.forEach(campaignDto -> {
                Integer totalClicks = campaignDto.getClicks();
                BigDecimal payouts = campaignDto.getPayouts();
                BigDecimal cpc = payouts.divide(new BigDecimal(totalClicks),RoundingMode.HALF_UP);
                String cpcStr = cpc.setScale(4,BigDecimal.ROUND_HALF_UP).toString();
                campaignDto.setCostPerClick(cpcStr);
                Integer totalLeads = campaignDto.getLeads();
                BigDecimal earnings = campaignDto.getEarnings();
                BigDecimal ppl = earnings.divide(new BigDecimal(totalLeads),RoundingMode.HALF_UP);
                String pplStr = ppl.setScale(4,BigDecimal.ROUND_HALF_UP).toString();
                campaignDto.setPayPerLead(pplStr);
                BigDecimal roi = earnings.subtract(payouts).divide(payouts,RoundingMode.HALF_UP).multiply(handren);
                String roiStr = roi.setScale(2,BigDecimal.ROUND_HALF_UP).toString();
                campaignDto.setRoi(roiStr);
            });
        }
        return campaignDtoPageInfo;
    }

    /**
     * 生成访问连接
     * @return
     */
    private String generateUrl(Integer campaignId, List<TokensParam> tokens){
        SystemConfig systemConfig = systemConfigDao.getByName(SystemConfigEnum.DOMAIN.getName());
        String domain = systemConfig.getValue();
        StringBuilder str = new StringBuilder();
        str.append(domain).append("/open/click/").append(campaignId).append("?");
        tokens.forEach(param -> {
            str.append(param.getName()).append("=").append(param.getValue()).append("&");
        });
        return str.substring(0,str.length()-1);
    }

    /**
     * 生成跳转链接
     * @param offerUrl
     * @return
     */
    private String generateRedirectUrl(String offerUrl, List<Tokens> tokens){
        if(StringUtils.isEmpty(tokens)){
            return offerUrl;
        }
        StringBuilder str = new StringBuilder();
        if(offerUrl.contains("?")){
            str.append(offerUrl);
            tokens.forEach(token -> {
                str.append("&").append(token.getName()).append("=").append(token.getValue());
            });
            return str.toString();
        }
        str.append(offerUrl).append("?");
        tokens.forEach(token -> {
            str.append(token.getName()).append("=").append(token.getValue()).append("&");
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
        campaign.setUrl("");
        //生成跳转链接
        List<Tokens> offerTokens = tokensDao.listByIdRefAndType(networkId,Tokens.TypeEnum.OFFER.getCode());
        String redirectUrl = generateRedirectUrl(offer.getUrl(),offerTokens);
        campaign.setRedirectUrl(redirectUrl);
        return campaign;
    }
}
