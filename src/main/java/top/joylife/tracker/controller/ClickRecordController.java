package top.joylife.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.joylife.tracker.common.ReData;
import top.joylife.tracker.common.dto.CampaignDto;
import top.joylife.tracker.common.util.ReUtil;
import top.joylife.tracker.dao.entity.Campaign;
import top.joylife.tracker.service.CampaignService;

import java.util.Map;

@RestController
@RequestMapping("clickRecord")
public class ClickRecordController {

    @Autowired
    private CampaignService campaignService;

    @RequestMapping(value = "/{campaignId}")
    public void recordLog(@PathVariable Integer campaignId, @RequestBody Map<String,Object> param){

        String link = campaignService.getLink(campaignId);

    }
}
