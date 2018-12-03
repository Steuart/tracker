package top.joylife.tracker.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.joylife.tracker.common.bean.dto.TransferRecordDto;
import top.joylife.tracker.common.bean.query.TransferRecordPageQuery;
import top.joylife.tracker.common.enums.QuotaEnum;
import top.joylife.tracker.common.util.BeanUtil;
import top.joylife.tracker.dao.entity.Campaign;
import top.joylife.tracker.dao.entity.ClickRecord;
import top.joylife.tracker.dao.entity.Traffic;
import top.joylife.tracker.dao.entity.TransferRecord;
import top.joylife.tracker.dao.impl.CampaignDao;
import top.joylife.tracker.dao.impl.ClickRecordDao;
import top.joylife.tracker.dao.impl.TrafficDao;
import top.joylife.tracker.dao.impl.TransferRecordDao;

import java.math.BigDecimal;
import java.util.*;

@Service
public class TransferRecordService {

    @Autowired
    private TransferRecordDao transferRecordDao;

    @Autowired
    private CampaignDao campaignDao;

    @Autowired
    private TrafficDao trafficDao;

    @Autowired
    private ClickRecordDao clickRecordDao;

    public PageInfo<TransferRecordDto> pageTransferRecord(TransferRecordPageQuery query){
        PageInfo<TransferRecord> pageInfo = transferRecordDao.pageQuery(query);
        PageInfo<TransferRecordDto> dtoPageInfo = BeanUtil.copy(pageInfo, TransferRecordDto.class);
        Set<Integer> campaignIds = new HashSet<>();
        Set<Integer> trafficIds = new HashSet<>();
        List<TransferRecordDto> dtoList = dtoPageInfo.getList();
        if(!CollectionUtils.isEmpty(dtoList)) {
            dtoList.forEach(dto -> {
                campaignIds.add(dto.getCampaignId());
                trafficIds.add(dto.getTrafficId());
            });
        }
        Map<Integer,Campaign> campaignMap = BeanUtil.generateMap(campaignIds,campaignDao,Campaign.class);
        Map<Integer,Traffic> trafficMap = BeanUtil.generateMap(trafficIds,trafficDao,Traffic.class);
        dtoList.forEach(dto -> {
            Campaign campaign = campaignMap.get(dto.getCampaignId());
            if(campaign!=null){
                dto.setCampaignName(campaign.getName());
            }

            Traffic traffic = trafficMap.get(dto.getTrafficId());
            if(traffic!=null){
                dto.setTrafficName(traffic.getName());
            }
        });
        return dtoPageInfo;
    }

    public void saveTransferRecord(Map<String,String[]> params){
        String clickRecordUuid = BeanUtil.getValueFromParams(QuotaEnum.CLICK_RECORD_UUID,params);
        String payout = BeanUtil.getValueFromParams(QuotaEnum.PAYOUT,params);
        ClickRecord clickRecord = clickRecordDao.getByUuid(clickRecordUuid);
        TransferRecord transferRecord = new TransferRecord();
        if(clickRecord!=null){
            transferRecord.setCampaignId(clickRecord.getCampaignId());
            transferRecord.setClickRecordId(clickRecord.getId());
            transferRecord.setTrafficId(clickRecord.getTrafficId());
        }
        transferRecord.setTransferDate(new Date());
        transferRecord.setContent(JSON.toJSONString(params));
        if(NumberUtils.isParsable(payout)){
            transferRecord.setEarnings(new BigDecimal(payout));
        }
        transferRecordDao.insert(transferRecord);
    }
}
