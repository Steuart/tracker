package top.joylife.tracker.cache;

import com.github.pagehelper.PageInfo;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.util.StringUtils;
import top.joylife.tracker.common.ErrorCode;
import top.joylife.tracker.common.bean.dto.UserDto;
import top.joylife.tracker.common.exception.Warning;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class AuthHolder {

    /**
     * token
     */
    public static final String TOKEN = "security_token";

    /**
     * 最大存活时间
     */
    public static final Integer MAX_AGE=7200;

    private static final Cache<String,UserDto> USER_INFO_CACHE = CacheBuilder.
            newBuilder().
            maximumSize(100).
            expireAfterAccess(MAX_AGE,TimeUnit.SECONDS).
            build();

    private static final Cache<String,String> USERNAME_TOKEN_CACHE = CacheBuilder.
            newBuilder().
            maximumSize(100).
            expireAfterAccess(MAX_AGE,TimeUnit.SECONDS).
            build();


    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    public static UserDto getUserByToken(String token){
        if(StringUtils.isEmpty(token)){
            return null;
        }
        String username = USERNAME_TOKEN_CACHE.getIfPresent(token);
        if(StringUtils.isEmpty(username)){
            return null;
        }
        return USER_INFO_CACHE.getIfPresent(username);
    }

    /**
     * 缓存用户信息
     * @param userDto
     */
    public static String cacheUserInfo(UserDto userDto){
        String token = UUID.randomUUID().toString();
        String username = userDto.getUsername();
        if(StringUtils.isEmpty(username)){
            throw new Warning(ErrorCode.user_name_can_not_be_empity);
        }
        USERNAME_TOKEN_CACHE.put(token,username);
        USER_INFO_CACHE.put(username,userDto);
        return token;
    }
}
