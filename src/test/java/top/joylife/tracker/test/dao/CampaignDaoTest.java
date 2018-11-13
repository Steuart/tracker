package top.joylife.tracker.test.dao;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.joylife.tracker.dao.entity.Campaign;
import top.joylife.tracker.dao.impl.CampaignDao;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CampaignDaoTest {

    @Autowired
    private CampaignDao campaignDao;

    @Test
    public void insertTest(){
        Campaign campaign = new Campaign();
        campaign.setClicks(10);
        campaign.setTrafficId(1);
        campaign.setNetworkId(1);
        campaign.setType(1);
        campaignDao.insert(campaign);
        log.info("id:{}",campaign.getId());
    }

    @Test
    public void getTest(){
        Campaign campaign = campaignDao.getById(1);
        log.info("campaign:{}", JSON.toJSONString(campaign));
    }

}
