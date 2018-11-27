package top.joylife.tracker.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.joylife.tracker.common.ErrorCode;
import top.joylife.tracker.common.bean.dto.QuotaDto;
import top.joylife.tracker.common.bean.dto.TrafficDto;
import top.joylife.tracker.common.bean.param.QuotaParam;
import top.joylife.tracker.common.exception.Warning;
import top.joylife.tracker.dao.entity.*;
import top.joylife.tracker.dao.impl.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 指标service
 */
@Service
public class QuotaService {

    @Autowired
    private QuotaDao quotaDao;

    @Autowired
    private CampaignTokenDao campaignTokenDao;

    @Autowired
    private TrafficTokenDao trafficTokenDao;

    @Autowired
    private CampaignDao campaignDao;

    @Autowired
    private TrafficDao trafficDao;

    /**
     * 列出所有的分组和指标
     * @return
     */
    public List<QuotaDto> listAll(){
        List<Quota> quotas = quotaDao.listAllQuota();
        List<QuotaDto> quotaDtos = new ArrayList<>();
        if(CollectionUtils.isEmpty(quotas)){
            return quotaDtos;
        }
        Map<Integer,List<QuotaDto>> groupMap = new HashMap<>();
        //寻找分组
        quotas.forEach(quota -> {
            if(quota.getGroupId()==0){
                QuotaDto group = new QuotaDto();
                BeanUtils.copyProperties(quota,group);
                quotaDtos.add(group);
                return;
            }
            Integer groupId = quota.getGroupId();
            List<QuotaDto> children = groupMap.computeIfAbsent(groupId,(k)->new ArrayList<>());
            QuotaDto quotaDto = new QuotaDto();
            BeanUtils.copyProperties(quota,quotaDto);
            children.add(quotaDto);
        });
        //分组
        quotaDtos.forEach(quotaDto -> {
            quotaDto.setChildren(groupMap.get(quotaDto.getId()));
        });
        return quotaDtos;
    }

    /**
     * 保存指标
     * @param param
     * @return
     */
    public Integer saveQuota(QuotaParam param){
        Quota quota = new Quota();
        BeanUtils.copyProperties(param,quota);
        return quotaDao.insert(quota);
    }

    /**
     * 更新指标
     * @param id
     * @param param
     * @return
     */
    public void updateQuota(Integer id, QuotaParam param){
        Quota quota = new Quota();
        BeanUtils.copyProperties(quota,param);
        quota.setId(id);
        quotaDao.updateById(quota);
    }

    /**
     * 删除指标
     * @param id
     */
    public void deleteQuota(Integer id){
        Quota quota = quotaDao.getById(id);
        if(quota == null){
            return;
        }
        String name = quota.getName();
        List<CampaignToken> campaignTokens = campaignTokenDao.selectByName(name);
        if(!CollectionUtils.isEmpty(campaignTokens)){
            List<Integer> campaignIds = new ArrayList<>();
            campaignTokens.forEach(campaignToken -> {
                campaignIds.add(campaignToken.getCampaignId());
            });
            List<Campaign> campaigns = campaignDao.listByIds(campaignIds,Campaign.class);
            if(!CollectionUtils.isEmpty(campaigns)){
                throw new Warning(ErrorCode.campaign_exists, campaigns);
            }
        }

        List<TrafficToken> trafficTokens = trafficTokenDao.selectByName(name);
        if(!CollectionUtils.isEmpty(trafficTokens)){
            List<Integer> trafficIds = new ArrayList<>();
            trafficTokens.forEach(trafficToken -> {
                trafficIds.add(trafficToken.getTrafficId());
            });
            List<Traffic> traffics = trafficDao.listByIds(trafficIds,Traffic.class);
            if(!CollectionUtils.isEmpty(traffics)){
                throw new Warning(ErrorCode.traffic_exists, traffics);
            }
        }

        quotaDao.deleteById(id);
    }
}
