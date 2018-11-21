package top.joylife.tracker.advice;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.joylife.tracker.common.ReData;
import top.joylife.tracker.common.exception.Warning;
import top.joylife.tracker.common.util.ReUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {


    @ExceptionHandler(value = { Exception.class })
    @ResponseBody
    public ReData<Map<String,Object>> exceptionHandler(HttpServletRequest req, Exception e){
        Map<String,String[]> params = req.getParameterMap();
        String requestUrl = req.getRequestURL().toString();
        log.error("something wrong, requestUrl:{},params:{}",requestUrl, JSON.toJSONString(params),e);
        String msg = "";
        String code = "0";
        if(e instanceof Warning){
            Warning warning = (Warning)e;
            msg = warning.getMsg();
            code = warning.getCode();
        }else{
            msg = e.getMessage();
        }
        Map<String,Object> data = new HashMap<>();
        data.put("requestUrl",requestUrl);
        return ReUtil.fail(code, msg, data);
    }
}
