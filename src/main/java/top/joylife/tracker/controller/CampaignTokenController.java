package top.joylife.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.joylife.tracker.common.ReData;
import top.joylife.tracker.common.bean.dto.CampaignTokenDto;
import top.joylife.tracker.common.util.ReUtil;
import top.joylife.tracker.dao.entity.CampaignToken;
import top.joylife.tracker.service.CampaignTokenService;

import java.util.List;

@RestController
@RequestMapping("campaignToken")
public class CampaignTokenController {

    @Autowired
    private CampaignTokenService campaignTokenService;

    /**
     * 根据campaignId查询列表
     * @param campaignId
     * @return
     */
    @GetMapping(value = "/list")
    ReData<List<CampaignTokenDto>> listByCampaignId(Integer campaignId){
        List<CampaignTokenDto> campaignTokenDtos = campaignTokenService.listByCampaignId(campaignId);
        return ReUtil.success(campaignTokenDtos);
    }
}
