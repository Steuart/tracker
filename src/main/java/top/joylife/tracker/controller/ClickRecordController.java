package top.joylife.tracker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.joylife.tracker.common.dto.CampaignDto;
import top.joylife.tracker.common.util.UuidUtil;
import top.joylife.tracker.service.CampaignService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("clickRecord")
@Slf4j
public class ClickRecordController {

    @Autowired
    private CampaignService campaignService;

    @RequestMapping(value = "/{campaignId}")
    public void recordLog(@PathVariable Integer campaignId,
                          @RequestBody(required = false) Map<String,Object> param,
                          HttpServletResponse response){
        CampaignDto campaignDto = campaignService.getCampaign(campaignId);
        String link = campaignDto.getRedirectLink();
        String clickRecordId = UuidUtil.getUuid();
        String redirectLink = String.format(link,clickRecordId);
        try {
            response.sendRedirect(redirectLink);
        } catch (IOException e) {
            log.error("重定向异常:redirectLink:{}",redirectLink,e);
        }
    }
}
