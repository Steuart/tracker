package top.joylife.tracker.common.util;

import java.security.SecureRandom;
import java.util.UUID;

public class UuidUtil {

    private static SecureRandom random = new SecureRandom();

    public static String uuid() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }

    /**
     * 使用SecureRandom随机生成Long.
     */
    public static long getLong() {
        return Math.abs(random.nextLong());
    }
}
