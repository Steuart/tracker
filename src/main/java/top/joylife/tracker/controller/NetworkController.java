package top.joylife.tracker.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.joylife.tracker.common.ReData;
import top.joylife.tracker.common.bean.dto.NetworkDto;
import top.joylife.tracker.common.bean.param.NetworkParam;
import top.joylife.tracker.common.util.ReUtil;
import top.joylife.tracker.service.NetworkService;

@RestController
@RequestMapping("network")
public class NetworkController {

    @Autowired
    private NetworkService networkService;

    @GetMapping(value = "/{id}")
    public ReData<NetworkDto> getById(@PathVariable Integer id){
        NetworkDto networkDto = networkService.getById(id);
        return ReUtil.success(networkDto);
    }

    @PutMapping(value = "/")
    public ReData<Integer> saveNetwork(@RequestBody NetworkParam param){
        Integer id = networkService.saveNetwork(param);
        return ReUtil.success(id);
    }

    @PostMapping(value = "/{id}")
    public ReData<Integer> updateNetwork(@PathVariable Integer id,
                                         @RequestBody NetworkParam param){
        networkService.updateNetwork(id,param);
        return ReUtil.success(id);
    }

    @DeleteMapping(value = "/{id}")
    public ReData<Integer> deleteNetwork(@PathVariable Integer id){
        networkService.deleteNetwork(id);
        return ReUtil.success(id);
    }
}
