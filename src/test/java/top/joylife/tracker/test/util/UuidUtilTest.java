package top.joylife.tracker.test.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import top.joylife.tracker.common.util.UuidUtil;

@Slf4j
public class UuidUtilTest {

    @Test
    public void generateUuid(){
        log.info(""+UuidUtil.getLong());
    }

    @Test
    public void other(){
        log.info(UuidUtil.getUuid());
    }
}
