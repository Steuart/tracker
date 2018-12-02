package top.joylife.tracker.interceptor;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import top.joylife.tracker.cache.AuthHolder;
import top.joylife.tracker.common.ErrorCode;
import top.joylife.tracker.common.ReData;
import top.joylife.tracker.common.bean.dto.UserDto;
import top.joylife.tracker.common.util.ReUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class AccessInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader("security_token");
        if(StringUtils.isEmpty(token)){
            buildResponse(response);
            return false;
        }
        UserDto userDto = AuthHolder.getUserByToken(token);
        if(userDto !=null){
            return true;
        }else{
            buildResponse(response);
            return false;
        }
    }

    private void buildResponse(HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        ReData<String> reData = ReUtil.fail(ErrorCode.login_expired);
        PrintWriter printWriter = response.getWriter();
        printWriter.append(JSON.toJSONString(reData));
    }

}
