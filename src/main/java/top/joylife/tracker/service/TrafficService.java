package top.joylife.tracker.service;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.joylife.tracker.common.bean.dto.TrafficDto;
import top.joylife.tracker.common.bean.param.TokensParam;
import top.joylife.tracker.common.bean.param.TrafficParam;
import top.joylife.tracker.common.bean.query.TrafficPageQuery;
import top.joylife.tracker.common.util.PageUtil;
import top.joylife.tracker.dao.entity.Tokens;
import top.joylife.tracker.dao.entity.Traffic;
import top.joylife.tracker.dao.impl.TokensDao;
import top.joylife.tracker.dao.impl.TrafficDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrafficService {

    @Autowired
    private TrafficDao trafficDao;

    @Autowired
    private TokensDao tokensDao;

    public TrafficDto getById(Integer id){
        Traffic traffic = trafficDao.getById(id);
        TrafficDto trafficDto = new TrafficDto();
        BeanUtils.copyProperties(traffic,trafficDto);
        return trafficDto;
    }

    public Integer saveTraffic(TrafficParam param){
        Traffic traffic = new Traffic();
        BeanUtils.copyProperties(param,traffic);
        trafficDao.insert(traffic);
        //保存campaignToken
        Integer trafficId = traffic.getId();
        List<Tokens> tokens = generateTrafficToken(trafficId, param.getTokens());
        tokensDao.deleteByIdRefAndType(trafficId,Tokens.TypeEnum.TRAFFIC.getCode());
        tokensDao.batchAddTokens(tokens);
        return traffic.getId();
    }

    public void updateTraffic(Integer id,TrafficParam param){
        Traffic traffic = new Traffic();
        BeanUtils.copyProperties(param,traffic);
        traffic.setId(id);
        trafficDao.updateById(traffic);
        //保存campaignToken
        List<Tokens> tokens = generateTrafficToken(id, param.getTokens());
        tokensDao.deleteByIdRefAndType(id,Tokens.TypeEnum.TRAFFIC.getCode());
        tokensDao.batchAddTokens(tokens);
    }

    public void deleteTraffic(Integer id){
        trafficDao.softDeleteById(id,Traffic.class);
    }

    public PageInfo<TrafficDto> pageQueryTraffic(TrafficPageQuery query){
        PageInfo<Traffic> pageInfo = trafficDao.pageQuery(query);
        return PageUtil.copy(pageInfo,TrafficDto.class);
    }

    /**
     * 获取流量平台列表
     * @return
     */
    public List<TrafficDto> listTraffic(){
        List<Traffic> traffics = trafficDao.list(Traffic.class);
        List<TrafficDto> trafficDtos = new ArrayList<>();
        if(!CollectionUtils.isEmpty(traffics)){
            traffics.forEach(traffic -> {
                TrafficDto trafficDto = new TrafficDto();
                BeanUtils.copyProperties(traffic,trafficDto);
                trafficDtos.add(trafficDto);
            });
        }
        return trafficDtos;
    }

    /**
     * 生成campaignToken
     * @param param
     * @return
     */
    private List<Tokens> generateTrafficToken(Integer trafficId, List<TokensParam> param){
        List<Tokens> tokens = new ArrayList<>();
        param.forEach(tokenParam ->{
            Tokens token = new Tokens();
            BeanUtils.copyProperties(tokenParam, token);
            token.setIdRef(trafficId);
            token.setType(Tokens.TypeEnum.TRAFFIC.getCode());
            tokens.add(token);
        });
        return tokens;
    }
}
