package top.joylife.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.joylife.tracker.common.ReData;
import top.joylife.tracker.common.dto.CampaignDto;
import top.joylife.tracker.common.util.ReUtil;
import top.joylife.tracker.service.CampaignService;

@RestController
@RequestMapping("campaign")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ReData<CampaignDto> getCampaign(@PathVariable Integer id){
        CampaignDto campaignDto = campaignService.getCampaign(id);
        return ReUtil.success(campaignDto);
    }
}
