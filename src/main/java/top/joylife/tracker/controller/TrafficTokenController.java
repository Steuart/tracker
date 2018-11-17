package top.joylife.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.joylife.tracker.common.ReData;
import top.joylife.tracker.common.bean.dto.TrafficTokenDto;
import top.joylife.tracker.common.bean.param.TrafficTokenParam;
import top.joylife.tracker.common.util.ReUtil;
import top.joylife.tracker.service.TrafficTokenService;

import java.util.List;

@RestController
@RequestMapping(value = "trafficToken")
public class TrafficTokenController {

    @Autowired
    private TrafficTokenService trafficTokenService;

    /**
     * 保存trafficToken
     * @param param
     * @return
     */
    @PutMapping(value = "")
    public ReData<Integer> saveTrafficToken(@RequestBody TrafficTokenParam param){
        Integer id = trafficTokenService.saveTrafficToken(param);
        return ReUtil.success(id);
    }

    /**
     * 更新trafficToken
     * @param id
     * @param param
     * @return
     */
    @PostMapping(value = "/{id}")
    public ReData<Integer> updateTrafficToken(@PathVariable Integer id,
                                              @RequestBody TrafficTokenParam param){
        trafficTokenService.updateTrafficToken(id,param);
        return ReUtil.success(id);
    }

    /**
     * 删除trafficToken
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ReData<Integer> deleteTrafficToken(@PathVariable Integer id){
        trafficTokenService.deleteTrafficToken(id);
        return ReUtil.success(id);
    }

    /**
     * 根据trafficId获取trafficToken列表
     * @param trafficId
     * @return
     */
    @GetMapping(value = "/list")
    public ReData<List<TrafficTokenDto>> listByTrafficId(Integer trafficId){
        List<TrafficTokenDto> trafficTokenDtos = trafficTokenService.listByTrafficId(trafficId);
        return ReUtil.success(trafficTokenDtos);
    }
}
