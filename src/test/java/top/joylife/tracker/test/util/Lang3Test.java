package top.joylife.tracker.test.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

@Slf4j
public class Lang3Test {

    @Test
    public void numberUtilTest() {
        log.info("xxx:{}",NumberUtils.isParsable("11.2"));
    }
}
