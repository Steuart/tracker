package top.joylife.tracker.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.joylife.tracker.common.ErrorCode;
import top.joylife.tracker.common.bean.dto.QuotaDto;
import top.joylife.tracker.common.bean.param.QuotaParam;
import top.joylife.tracker.common.exception.Warning;
import top.joylife.tracker.dao.entity.*;
import top.joylife.tracker.dao.impl.*;

import java.sql.SQLIntegrityConstraintViolationException;
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
        //如果分组id为null，则设置为0，作为分组
        if(quota.getGroupId() == null){
            quota.setGroupId(0);
        }
        try{
            quotaDao.insert(quota);
        }catch (Exception e){
            if(e instanceof DuplicateKeyException){
                throw new Warning(ErrorCode.quota_code_duplicate);
            }
        }
        return quota.getId();
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
        if(Quota.DeleteAbleEnum.NO.getCode().equals(quota.getDeleteAble())){
            throw new Warning(ErrorCode.quota_can_not_delete);
        }
        if(Quota.TypeEnum.GROUP.getCode().equals(quota.getType())){
            deleteGroupCheck(quota.getGroupId());
        }else{
            deleteQuotaCheck(quota.getName());
        }
        quotaDao.deleteById(id);
    }

    /**
     * 删除指标校验
     * @param name
     */
    private void deleteQuotaCheck(String name) {
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
    }

    /**
     * 删除分组校验
     * @param id
     */
    private void deleteGroupCheck(Integer id) {
        List<Quota> quotas = quotaDao.selectByGroupId(id);
        if(!CollectionUtils.isEmpty(quotas)){
            throw new Warning(ErrorCode.quota_is_not_empty);
        }
    }
}
