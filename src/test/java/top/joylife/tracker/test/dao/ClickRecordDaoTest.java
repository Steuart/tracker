package top.joylife.tracker.test.dao;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.joylife.tracker.dao.entity.ClickRecord;
import top.joylife.tracker.dao.impl.ClickRecordDao;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ClickRecordDaoTest {

    @Autowired
    private ClickRecordDao clickRecordDao;

    @Test
    public void getById(){
        ClickRecord clickRecord = clickRecordDao.getById(1);
        String content = clickRecord.getClickContent();
        Map<String,Object> contentMap = JSON.parseObject(content,HashMap.class);
        log.info(Objects.toString(contentMap.get("name"),""));
    }

    @Test
    public void insert(){
        ClickRecord clickRecord = new ClickRecord();
        clickRecord.setUuid("xxxxxxxxxxxx");
        clickRecord.setCampaignId(1);
        clickRecord.setNetworkId(1);
        clickRecord.setOfferId(1);
        clickRecord.setTrafficId(1);
        Map<String,Object> content = new HashMap<>();
        content.put("xxx","xxx");
        content.put("asdasd","asdasd");
        clickRecord.setClickContent(JSON.toJSONString(content));
        clickRecordDao.insert(clickRecord);
    }
}
