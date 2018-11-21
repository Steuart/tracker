package top.joylife.tracker.test.lombok;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import top.joylife.tracker.dao.entity.User;

@Slf4j
public class LombokTest {

    @Test
    public void equalsTest(){
        User user1 = new User();
        user1.setId(1);
        User user2 = new User();
        user2.setId(1);
        log.info("xxxxxxxxxx{}",user1.equals(user2));
    }

    @Test
    public void toStringTest(){
        User user1 = new User();
        user1.setId(1);
        user1.setPassword("xxxxxxxxx");
        log.info(user1.toString());
    }
}
