package top.joylife.tracker.controller;


import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.joylife.tracker.common.ReData;
import top.joylife.tracker.common.bean.dto.NetworkDto;
import top.joylife.tracker.common.bean.param.NetworkParam;
import top.joylife.tracker.common.bean.query.NetworkPageQuery;
import top.joylife.tracker.common.util.ReUtil;
import top.joylife.tracker.service.NetworkService;

import java.util.List;

@RestController
@RequestMapping("network")
public class NetworkController {

    @Autowired
    private NetworkService networkService;

    /**
     * 根据id获取network
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ReData<NetworkDto> getById(@PathVariable Integer id){
        NetworkDto networkDto = networkService.getById(id);
        return ReUtil.success(networkDto);
    }

    /**
     * 分页获取network
     * @param query
     * @return
     */
    @GetMapping(value = "/page")
    public ReData<PageInfo<NetworkDto>> pageNetwork(NetworkPageQuery query){
        if(query==null){
            query = new NetworkPageQuery();
        }
        PageInfo<NetworkDto> networkDtoPageInfo = networkService.pageQueryNetWork(query);
        return ReUtil.success(networkDtoPageInfo);
    }

    /**
     * 查询网络联盟列表
     * @return
     */
    @GetMapping(value = "/list")
    public ReData<List<NetworkDto>> listNetwork(){
        List<NetworkDto> networkDtos = networkService.listNetwork();
        return ReUtil.success(networkDtos);
    }

    /**
     * 保存network
     * @param param
     * @return
     */
    @PutMapping(value = "")
    public ReData<Integer> saveNetwork(@RequestBody NetworkParam param){
        Integer id = networkService.saveNetwork(param);
        return ReUtil.success(id);
    }

    /**
     * 更新network
     * @param id
     * @param param
     * @return
     */
    @PostMapping(value = "/{id}")
    public ReData<Integer> updateNetwork(@PathVariable Integer id,
                                         @RequestBody NetworkParam param){
        networkService.updateNetwork(id,param);
        return ReUtil.success(id);
    }

    /**
     * 删除network
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ReData<Integer> deleteNetwork(@PathVariable Integer id){
        networkService.deleteNetwork(id);
        return ReUtil.success(id);
    }
}
