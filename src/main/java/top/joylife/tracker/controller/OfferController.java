package top.joylife.tracker.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.joylife.tracker.common.ReData;
import top.joylife.tracker.common.bean.dto.OfferDto;
import top.joylife.tracker.common.bean.param.OfferParam;
import top.joylife.tracker.common.bean.query.OfferPageQuery;
import top.joylife.tracker.common.util.ReUtil;
import top.joylife.tracker.service.OfferService;

@RestController
@RequestMapping("offer")
public class OfferController {

    @Autowired
    private OfferService offerService;

    /**
     * 根据id获取offer
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ReData<OfferDto> getById(@PathVariable Integer id){
        OfferDto offerDto = offerService.getById(id);
        return ReUtil.success(offerDto);
    }

    /**
     * 保存offer
     * @param param
     * @return
     */
    @PutMapping(value = "")
    public ReData<Integer> saveOffer(@RequestBody OfferParam param){
        Integer id = offerService.saveOffer(param);
        return ReUtil.success(id);
    }

    /**
     * 更新offer
     * @param id
     * @param param
     * @return
     */
    @PostMapping(value = "/{id}")
    public ReData<Integer> updateOffer(@PathVariable Integer id,
                                         @RequestBody OfferParam param){
        offerService.updateOffer(id,param);
        return ReUtil.success(id);
    }

    /**
     * 删除offer
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ReData<Integer> deleteOffer(@PathVariable Integer id){
        offerService.deleteOffer(id);
        return ReUtil.success(id);
    }

    /**
     * 分页获取offer
     * @param query
     * @return
     */
    @GetMapping(value = "/page")
    public ReData<PageInfo<OfferDto>> pageOffer(OfferPageQuery query){
        if(query==null){
            query = new OfferPageQuery();
        }
        PageInfo<OfferDto> offerDtoPageInfo = offerService.pageQueryOffer(query);
        return ReUtil.success(offerDtoPageInfo);
    }
}
