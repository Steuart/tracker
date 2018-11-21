package top.joylife.tracker.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.joylife.tracker.common.bean.dto.TrafficTokenDto;
import top.joylife.tracker.common.bean.param.TrafficTokenParam;
import top.joylife.tracker.dao.entity.TrafficToken;
import top.joylife.tracker.dao.impl.TrafficTokenDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrafficTokenService {

    @Autowired
    private TrafficTokenDao trafficTokenDao;

    /**
     * 保存trafficToken
     * @param trafficTokenParam
     * @return
     */
    public Integer saveTrafficToken(TrafficTokenParam trafficTokenParam){
        TrafficToken trafficToken = new TrafficToken();
        BeanUtils.copyProperties(trafficTokenParam,trafficToken);
        trafficTokenDao.insert(trafficToken);
        return trafficToken.getId();
    }

    /**
     * 更新trafficToken
     * @param id
     * @param param
     * @return
     */
    public Integer updateTrafficToken(Integer id,TrafficTokenParam param){
        TrafficToken trafficToken = new TrafficToken();
        BeanUtils.copyProperties(param,trafficToken);
        trafficToken.setId(id);
        trafficTokenDao.updateById(trafficToken);
        return id;
    }

    /**
     * 删除trafficToken
     * @param id
     */
    public void deleteTrafficToken(Integer id){
        trafficTokenDao.deleteById(id);
    }

    /**
     * 根据trafficId查询列表
     * @param trafficId
     * @return
     */
    public List<TrafficTokenDto> listByTrafficId(Integer trafficId){
        List<TrafficTokenDto> trafficTokenDtos = new ArrayList<>();
        List<TrafficToken> trafficTokens = trafficTokenDao.listByTrafficId(trafficId);
        if(CollectionUtils.isEmpty(trafficTokens)){
            return trafficTokenDtos;
        }
        trafficTokens.forEach(trafficToken -> {
            TrafficTokenDto trafficTokenDto = new TrafficTokenDto();
            BeanUtils.copyProperties(trafficToken,trafficTokenDto);
            trafficTokenDtos.add(trafficTokenDto);
        });
        return trafficTokenDtos;
    }
}
