package top.joylife.tracker.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
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

import java.util.*;

/**
 * 指标service
 */
@Service
@Slf4j
public class QuotaService {

    @Autowired
    private QuotaDao quotaDao;

    @Autowired
    private TokensDao tokensDao;

    @Autowired
    private CampaignDao campaignDao;

    @Autowired
    private TrafficDao trafficDao;

    @Autowired
    private NetworkDao networkDao;

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
            log.error("保存指标异常：param:{}", JSON.toJSONString(param),e);
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
        BeanUtils.copyProperties(param, quota);
        quota.setId(id);
        try{
            quotaDao.updateById(quota);
        }catch (Exception e){
            if(e instanceof DuplicateKeyException){
                throw new Warning(ErrorCode.quota_code_duplicate);
            }
            log.error("更新指标异常，id:{},param:{}",id,JSON.toJSONString(param),e);
        }
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
            deleteGroupCheck(quota.getId());
        }else{
            deleteQuotaCheck(quota.getCode());
        }
        quotaDao.deleteById(id);
    }

    /**
     * 删除指标校验
     * @param name
     */
    private void deleteQuotaCheck(String name) {
        List<Tokens> tokens = tokensDao.selectByName(name);
        if(!CollectionUtils.isEmpty(tokens)){
            Set<Integer> campaignIds = new HashSet<>();
            Set<Integer> networkIds = new HashSet<>();
            Set<Integer> trafficIds = new HashSet<>();
            tokens.forEach(token -> {
                Tokens.TypeEnum typeEnum = Tokens.TypeEnum.getByCode(token.getType());
                switch (typeEnum){
                    case CAMPAIGN:
                        campaignIds.add(token.getIdRef());
                        break;
                    case OFFER:
                        networkIds.add(token.getIdRef());
                        break;
                    case TRAFFIC:
                        trafficIds.add(token.getIdRef());
                        break;
                    case CALLBACK:
                        networkIds.add(token.getIdRef());
                        break;
                }
            });
            Map<String,Object> result = new HashMap<>();
            if(!CollectionUtils.isEmpty(campaignIds)) {
                List<Campaign> campaigns = campaignDao.listByIds(new ArrayList<>(campaignIds),Campaign.class);
                result.put("campaign",campaigns);
            }

            if(!CollectionUtils.isEmpty(networkIds)) {
                List<Network> networks = networkDao.listByIds(new ArrayList<>(networkIds),Network.class);
                result.put("network",networks);
            }

            if(!CollectionUtils.isEmpty(trafficIds)) {
                List<Traffic> traffics = trafficDao.listByIds(new ArrayList<>(trafficIds),Traffic.class);
                result.put("traffics",traffics);
            }

            if(!CollectionUtils.isEmpty(result)){
                throw new Warning(ErrorCode.campaign_exists, result);
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
