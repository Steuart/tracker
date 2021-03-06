package top.joylife.tracker.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.mvc.condition.ParamsRequestCondition;
import top.joylife.tracker.common.bean.dto.CampaignDto;
import top.joylife.tracker.common.bean.dto.ClickRecordDto;
import top.joylife.tracker.common.bean.query.ClickRecordPageQuery;
import top.joylife.tracker.common.enums.QuotaEnum;
import top.joylife.tracker.common.util.BeanUtil;
import top.joylife.tracker.dao.entity.*;
import top.joylife.tracker.dao.impl.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
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
    public void saveClickRecord(String uuid, CampaignDto campaignDto, HttpServletRequest request){
        Map<String,String[]> params = request.getParameterMap();
        Map<String,Object> result = parseRequest(request);
        ClickRecord record = new ClickRecord();
        record.setUuid(uuid);
        record.setTrafficId(campaignDto.getTrafficId());
        record.setOfferId(campaignDto.getOfferId());
        record.setNetworkId(campaignDto.getNetworkId());
        record.setCampaignId(campaignDto.getId());
        String[] payoutArr = params.get(QuotaEnum.PAYOUT.getCode());
        BigDecimal cost = new BigDecimal(0);
        if(payoutArr!=null && payoutArr.length!=0){
            String payout = payoutArr[0];
            if(NumberUtils.isParsable(payout)){
                cost = new BigDecimal(payout);
            }
        }
        record.setPayout(cost);
        String content = JSON.toJSONString(result);
        record.setClickContent(content);
        clickRecordDao.insert(record);
        campaignDao.addClickCount(campaignDto.getId(),cost);
    }

    /**
     * 解析httpRequest
     * @param request
     * @return
     */
    private Map<String,Object> parseRequest(HttpServletRequest request){
        Map<String,String[]> params = request.getParameterMap();
        Map<String,Object> result = new HashMap<>();
        for (Map.Entry<String,String[]> param:params.entrySet()){
            String name = param.getKey();
            String[] value = param.getValue();
            if(value!=null && value.length!=0){
                result.put(name,value[0]);
            }
        }
        Enumeration<String> heads = request.getHeaderNames();
        while (heads.hasMoreElements()){
            String head = heads.nextElement();
            String value = request.getHeader(head);
            result.put(head,value);
        }
        result.put(QuotaEnum.REMOTE_IP.getCode(),request.getRemoteAddr());
        result.put(QuotaEnum.LOCALE.getCode(),JSON.toJSONString(request.getLocale()));
        return result;
    }


    /**
     * 保存转化记录
     * @param params
     */
    public void saveTransferRecord(Map<String,String[]> params){
        String clickRecordUuid = BeanUtil.getValueFromParams(QuotaEnum.CLICK_RECORD_UUID,params);
        String payout = BeanUtil.getValueFromParams(QuotaEnum.PAYOUT,params);
        ClickRecord clickRecord = clickRecordDao.getByUuid(clickRecordUuid);
        if(clickRecord==null){
            log.error("点击记录为空，uuid:{},params:{}",clickRecordUuid,JSON.toJSONString(params));
            return;
        }
        ClickRecord clickRecordForUpdate = new ClickRecord();

        clickRecordForUpdate.setId(clickRecord.getId());
        clickRecordForUpdate.setPayout(clickRecord.getPayout());
        clickRecordForUpdate.setStatus(ClickRecord.StatusEnum.TRANSFER.getCode());
        clickRecordForUpdate.setTransferDate(new Date());
        clickRecordForUpdate.setTransferContent(JSON.toJSONString(params));
        BigDecimal earning = new BigDecimal(0);
        if(NumberUtils.isParsable(payout)){
            earning = new BigDecimal(payout);
            clickRecordForUpdate.setEarning(earning);
        }
        clickRecordDao.updateById(clickRecordForUpdate);
        campaignDao.addLeadsCount(clickRecord.getCampaignId(),earning);
    }

    /**
     * 分页获取记录
     * @param query
     * @return
     */
    public PageInfo<ClickRecordDto> pageClickRecord(ClickRecordPageQuery query){
        PageInfo<ClickRecord> clickRecordDtoPageInfo = clickRecordDao.pageQuery(query);
        PageInfo<ClickRecordDto> clickRecordDtoInfo =  BeanUtil.copy(clickRecordDtoPageInfo,ClickRecordDto.class);
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
        Map<Integer,Offer> offerMap = BeanUtil.generateMap(offerIds,offerDao,Offer.class);
        Map<Integer,Campaign> campaignMap = BeanUtil.generateMap(campaignIds,campaignDao,Campaign.class);
        Map<Integer,Traffic> trafficMap = BeanUtil.generateMap(trafficIds,trafficDao,Traffic.class);
        Map<Integer,Network> networkMap = BeanUtil.generateMap(networkIds,networkDao,Network.class);
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
            clickRecordDto.setClickContent(JSON.parseObject(clickRecord.getClickContent(),HashMap.class));
            clickRecordDto.setTransferContent(JSON.parseObject(clickRecord.getTransferContent(),HashMap.class));
        });
        return clickRecordDtoInfo;
    }
}
