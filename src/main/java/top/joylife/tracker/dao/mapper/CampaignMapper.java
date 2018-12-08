package top.joylife.tracker.dao.mapper;

import org.apache.ibatis.annotations.Param;
import top.joylife.tracker.dao.MyMapper;
import top.joylife.tracker.dao.entity.Campaign;

import java.math.BigDecimal;

public interface CampaignMapper extends MyMapper<Campaign> {


    /**
     * 添加点击数
     */
    void addClickCount(@Param("id") Integer id, @Param("payout") BigDecimal payout);

    /**
     * 添加转化数
     */
    void addLeadsCount(@Param("id") Integer id,@Param("earning") BigDecimal earning);

}