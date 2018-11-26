package top.joylife.tracker.test.service;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.joylife.tracker.common.bean.dto.QuotaDto;
import top.joylife.tracker.service.QuotaService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class QuotaServiceTest {

    @Autowired
    private QuotaService quotaService;

    @Test
    public void listAllTest(){
        List<QuotaDto> quotaDtoList = quotaService.listAll();
        log.info("xxxxxx:{}", JSON.toJSONString(quotaDtoList,true));
    }
}
