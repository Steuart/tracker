package top.joylife.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.joylife.tracker.common.ReData;
import top.joylife.tracker.common.bean.dto.TrafficDto;
import top.joylife.tracker.common.bean.param.TrafficParam;
import top.joylife.tracker.common.util.ReUtil;
import top.joylife.tracker.service.TrafficService;

@RestController
@RequestMapping("traffic")
public class TrafficController {
    @Autowired
    private TrafficService trafficService;

    @GetMapping(value = "/{id}")
    public ReData<TrafficDto> getById(@PathVariable Integer id){
        TrafficDto trafficDto = trafficService.getById(id);
        return ReUtil.success(trafficDto);
    }

    @PutMapping(value = "/")
    public ReData<Integer> saveNetwork(@RequestBody TrafficParam param){
        Integer id = trafficService.saveTraffic(param);
        return ReUtil.success(id);
    }

    @PostMapping(value = "/{id}")
    public ReData<Integer> updateNetwork(@PathVariable Integer id,
                                         @RequestBody TrafficParam param){
        trafficService.updateTraffic(id,param);
        return ReUtil.success(id);
    }

    @DeleteMapping(value = "/{id}")
    public ReData<Integer> deleteNetwork(@PathVariable Integer id){
        trafficService.deleteTraffic(id);
        return ReUtil.success(id);
    }
}
