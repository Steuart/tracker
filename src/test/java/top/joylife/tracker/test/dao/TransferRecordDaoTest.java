package top.joylife.tracker.test.dao;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.joylife.tracker.common.bean.dto.TransferCountDto;
import top.joylife.tracker.common.util.DateUtil;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TransferRecordDaoTest {

    @Autowired
    private TransferRecordDao recordDao;

    @Test
    public void transferCountTest(){
        Date now = new Date();
        Date beginDate = DateUtil.getDayBeginTime(now);
        Date endDate = DateUtil.getDayEndTime(now);
        TransferCountDto dto = recordDao.selectTransferCount(beginDate,endDate);
        log.info("xxxx:{}", JSON.toJSONString(dto));
    }
}
