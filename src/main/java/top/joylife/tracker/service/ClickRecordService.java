package top.joylife.tracker.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.joylife.tracker.common.bean.dto.ClickRecordDto;
import top.joylife.tracker.common.bean.param.ClickRecordParam;
import top.joylife.tracker.common.bean.query.ClickRecordPageQuery;
import top.joylife.tracker.common.util.PageUtil;
import top.joylife.tracker.dao.entity.*;
import top.joylife.tracker.dao.impl.*;
import top.joylife.tracker.dao.mapper.CampaignMapper;
import top.joylife.tracker.dao.mapper.NetworkMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class ClickRecordService {

    @Autowired
    private ClickRecordDao clickRecordDao;

    @Autowired
    private OfferDao offerDao;

    @Autowired
    private CampaignDao campaignDao;

    @Autowired
    private TrafficDao trafficDao;

    @Autowired
    private NetworkDao networkDao;

    /**
     * 保存点击记录
     * @param uuid
     * @param request
     */
    @Async
    public void saveClickRecord(String uuid, HttpServletRequest request){
        Map<String,String[]> params = request.getParameterMap();
        ClickRecord record = new ClickRecord();
        record.setUuid(uuid);
        String content = JSON.toJSONString(params);
        record.setContent(content);
        clickRecordDao.insert(record);
    }


    /**
     * 分页获取记录
     * @param query
     * @return
     */
    public PageInfo<ClickRecordDto> pageClickRecord(ClickRecordPageQuery query){
        PageInfo<ClickRecord> clickRecordDtoPageInfo = clickRecordDao.pageQuery(query);
        PageInfo<ClickRecordDto> clickRecordDtoInfo =  PageUtil.copy(clickRecordDtoPageInfo,ClickRecordDto.class);
        List<ClickRecordDto> clickRecordDtos = clickRecordDtoInfo.getList();
        if(CollectionUtils.isEmpty(clickRecordDtos)){
            return clickRecordDtoInfo;
        }

        Map<Integer,ClickRecord> clickRecordMap = new HashMap<>();
        List<ClickRecord> clickRecords = clickRecordDtoPageInfo.getList();
        clickRecords.forEach(clickRecord -> {
            clickRecordMap.put(clickRecord.getId(),clickRecord);
        });
        Set<Integer> offerIds = new HashSet<>();
        Set<Integer> campaignIds = new HashSet<>();
        Set<Integer> trafficIds = new HashSet<>();
        Set<Integer> networkIds = new HashSet<>();
        clickRecordDtos.forEach(clickRecordDto -> {
            offerIds.add(clickRecordDto.getOfferId());
            campaignIds.add(clickRecordDto.getCampaignId());
            trafficIds.add(clickRecordDto.getTrafficId());
            networkIds.add(clickRecordDto.getNetworkId());
        });
        Map<Integer,Offer> offerMap = generateMap(offerIds,offerDao,Offer.class);
        Map<Integer,Campaign> campaignMap = generateMap(campaignIds,campaignDao,Campaign.class);
        Map<Integer,Traffic> trafficMap = generateMap(trafficIds,trafficDao,Traffic.class);
        Map<Integer,Network> networkMap = generateMap(networkIds,networkDao,Network.class);
        //补全信息
        clickRecordDtos.forEach(clickRecordDto -> {
            Offer offer = offerMap.get(clickRecordDto.getOfferId());
            if(offer!=null){
                clickRecordDto.setOfferName(offer.getName());
            }
            Campaign campaign = campaignMap.get(clickRecordDto.getCampaignId());
            if(campaign!=null){
                clickRecordDto.setCampaignName(campaign.getName());
            }
            Traffic traffic = trafficMap.get(clickRecordDto.getTrafficId());
            if(traffic!=null){
                clickRecordDto.setTrafficName(traffic.getName());
            }
            Network network = networkMap.get(clickRecordDto.getNetworkId());
            if(network!=null){
                clickRecordDto.setNetworkName(network.getName());
            }
            ClickRecord clickRecord = clickRecordMap.get(clickRecordDto.getId());
            clickRecordDto.setContent(JSON.parseObject(clickRecord.getContent(),HashMap.class));
        });
        return clickRecordDtoInfo;
    }

    /**
     * 生成map映射
     * @param ids
     * @param dao
     * @param t
     * @param <T>
     * @return
     */
    private <T extends BaseEntity> Map<Integer,T> generateMap(Set<Integer> ids, BaseDao<T> dao, Class<T> t){
        List<T> lists = dao.listByIds(new ArrayList<>(ids),t);
        Map<Integer,T> result = new HashMap<>();
        for(T list:lists){
            result.put(list.getId(),list);
        }
        return result;
    }
}
