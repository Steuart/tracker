package top.joylife.tracker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import top.joylife.tracker.common.bean.dto.CampaignDto;
import top.joylife.tracker.common.bean.param.ClickRecordParam;
import top.joylife.tracker.common.util.UuidUtil;
import top.joylife.tracker.service.CampaignService;
import top.joylife.tracker.service.ClickRecordService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("clickRecord")
@Slf4j
public class ClickRecordController {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private ClickRecordService clickRecordService;

    @RequestMapping(value = "/{campaignId}")
    public void recordLog(@PathVariable Integer campaignId,
                          @RequestBody(required = false) ClickRecordParam param,
                          HttpServletResponse response,HttpServletRequest request){
        CampaignDto campaignDto = campaignService.getCampaign(campaignId);
        String link = campaignDto.getRedirectLink();
        String clickRecordId = UuidUtil.getUuid();
        clickRecordService.saveClickRecord(clickRecordId,param,request);
        String redirectLink = String.format(link,clickRecordId);
        try {
            response.sendRedirect(redirectLink);
        } catch (IOException e) {
            log.error("重定向异常:redirectLink:{}",redirectLink,e);
        }
    }
}
