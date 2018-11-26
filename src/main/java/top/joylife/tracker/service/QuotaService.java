package top.joylife.tracker.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.joylife.tracker.common.bean.dto.QuotaDto;
import top.joylife.tracker.dao.entity.Quota;
import top.joylife.tracker.dao.impl.QuotaDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuotaService {

    @Autowired
    private QuotaDao quotaDao;

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
}
