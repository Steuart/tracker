package top.joylife.tracker.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.joylife.tracker.common.ReData;
import top.joylife.tracker.common.bean.dto.CampaignDto;
import top.joylife.tracker.common.bean.param.CampaignParam;
import top.joylife.tracker.common.bean.query.CampaignPageQuery;
import top.joylife.tracker.common.util.ReUtil;
import top.joylife.tracker.service.CampaignService;

@RestController
@RequestMapping("campaign")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    /**
     * 根据id获取项目
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ReData<CampaignDto> getCampaign(@PathVariable Integer id){
        CampaignDto campaignDto = campaignService.getCampaign(id);
        return ReUtil.success(campaignDto);
    }

    /**
     * 保存项目
     * @param param
     * @return
     */
    @PutMapping(value = "")
    public ReData<Integer> saveCampaign(@RequestBody CampaignParam param){
        Integer campaignId = campaignService.saveCampaign(param);
        return ReUtil.success(campaignId);
    }

    /**
     * 更新项目
     * @param id
     * @param param
     * @return
     */
    @PostMapping(value = "/{id}")
    public ReData<Integer> updateCampaign(@PathVariable Integer id,
                                          @RequestBody CampaignParam param){
        param.setId(id);
        campaignService.updateCampaign(param);
        return ReUtil.success(id);
    }

    /**
     * 分页获取项目列表
     * @param query
     * @return
     */
    @GetMapping(value = "/page")
    public ReData<PageInfo<CampaignDto>> pageCampaign(@RequestBody CampaignPageQuery query){
        PageInfo<CampaignDto> campaignDtoPageInfo =  campaignService.pageCampaign(query);
        return ReUtil.success(campaignDtoPageInfo);
    }
}
