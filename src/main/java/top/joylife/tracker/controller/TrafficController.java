package top.joylife.tracker.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.joylife.tracker.common.ReData;
import top.joylife.tracker.common.bean.dto.TrafficDto;
import top.joylife.tracker.common.bean.param.TrafficParam;
import top.joylife.tracker.common.bean.query.TrafficPageQuery;
import top.joylife.tracker.common.util.ReUtil;
import top.joylife.tracker.service.TrafficService;

import java.util.List;

@RestController
@RequestMapping("traffic")
public class TrafficController {
    @Autowired
    private TrafficService trafficService;

    /**
     * 根据id获取traffic
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ReData<TrafficDto> getById(@PathVariable Integer id){
        TrafficDto trafficDto = trafficService.getById(id);
        return ReUtil.success(trafficDto);
    }

    /**
     * 保存traffic
     * @param param
     * @return
     */
    @PutMapping(value = "")
    public ReData<Integer> saveTraffic(@RequestBody TrafficParam param){
        Integer id = trafficService.saveTraffic(param);
        return ReUtil.success(id);
    }

    /**
     * 更新traffic
     * @param id
     * @param param
     * @return
     */
    @PostMapping(value = "/{id}")
    public ReData<Integer> updateTraffic(@PathVariable Integer id,
                                         @RequestBody TrafficParam param){
        trafficService.updateTraffic(id,param);
        return ReUtil.success(id);
    }

    /**
     * 删除traffic
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ReData<Integer> deleteTraffic(@PathVariable Integer id){
        trafficService.deleteTraffic(id);
        return ReUtil.success(id);
    }

    /**
     * 分页获取traffic列表
     * @param query
     * @return
     */
    @GetMapping(value = "/page")
    public ReData<PageInfo<TrafficDto>> pageTraffic(TrafficPageQuery query){
        if(query == null){
            query = new TrafficPageQuery();
        }
        PageInfo<TrafficDto> trafficDtoPageInfo = trafficService.pageQueryTraffic(query);
        return ReUtil.success(trafficDtoPageInfo);
    }

    /**
     * 获取流量平台列表
     * @return
     */
    @GetMapping(value = "/list")
    public ReData<List<TrafficDto>> listTraffic(){
        List<TrafficDto> trafficDtos = trafficService.listTraffic();
        return ReUtil.success(trafficDtos);
    }
}
