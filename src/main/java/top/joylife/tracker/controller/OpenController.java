package top.joylife.tracker.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.joylife.tracker.common.ReData;
import top.joylife.tracker.common.bean.dto.CampaignDto;
import top.joylife.tracker.common.enums.QuotaEnum;
import top.joylife.tracker.common.util.ReUtil;
import top.joylife.tracker.common.util.UuidUtil;
import top.joylife.tracker.service.CampaignService;
import top.joylife.tracker.service.ClickRecordService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping(value = "open")
@Slf4j
public class OpenController {


    @Autowired
    private CampaignService campaignService;

    @Autowired
    private ClickRecordService clickRecordService;

    /**
     * 记录点击日志
     * @param campaignId
     * @param response
     * @param request
     */
    @RequestMapping(value = "/click/{campaignId}")
    @ResponseBody
    public void recordLog(@PathVariable Integer campaignId,
                          HttpServletResponse response, HttpServletRequest request){
        CampaignDto campaignDto = campaignService.getCampaign(campaignId);
        String link = campaignDto.getRedirectUrl();
        String clickRecordId = UuidUtil.getUuid();
        clickRecordService.saveClickRecord(clickRecordId,campaignDto,request);
        String redirectLink = link.replace(QuotaEnum.CLICK_RECORD_UUID.getCode(),clickRecordId);
        try {
            response.sendRedirect(redirectLink);
        } catch (IOException e) {
            log.error("重定向异常:redirectLink:{}",redirectLink,e);
        }
    }

    /**
     * 回调
     * @return
     */
    @RequestMapping(value = "/callback")
    @ResponseBody
    public ReData<String> callBack(HttpServletRequest request){
        Map<String,String []> maps = request.getParameterMap();
        clickRecordService.saveTransferRecord(maps);
        return ReUtil.success("success");
    }


    /**
     * 回调
     * @return
     */
    @RequestMapping(value = "/test")
    @ResponseBody
    public ReData<String> test(HttpServletRequest request){
        Map<String,String []> maps = request.getParameterMap();
        log.info("test:{}", JSON.toJSONString(maps));
        return ReUtil.success("success");
    }
}
