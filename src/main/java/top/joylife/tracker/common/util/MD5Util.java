package top.joylife.tracker.common.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.security.MessageDigest;

@Slf4j
public class MD5Util {


    /**
     * md5加密
     * @param str
     * @return
     */
    public static String getMD5Str(String str){
        String result = null;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            result = new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            log.error("MD5加密出现错误，"+e.toString());
        }
        return result;
    }
}
