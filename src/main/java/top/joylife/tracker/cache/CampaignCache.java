package top.joylife.tracker.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import top.joylife.tracker.common.bean.dto.CampaignDto;

import java.util.concurrent.TimeUnit;

public class CampaignCache {

    /**
     * 最大存活时间
     */
    public static final Integer MAX_AGE=7200;

    private static final Cache<Integer,CampaignDto> CAMPAIGN = CacheBuilder.
            newBuilder().
            maximumSize(1000).
            expireAfterAccess(MAX_AGE,TimeUnit.SECONDS).
            build();


    /**
     * 保存缓存
     * @param campaignDto
     */
    public static void saveCache(CampaignDto campaignDto){
        CAMPAIGN.put(campaignDto.getId(),campaignDto);
    }

    /**
     * 从缓存中获取
     * @param id
     * @return
     */
    public static CampaignDto getFromCache(Integer id){
        return CAMPAIGN.getIfPresent(id);
    }
}
