package top.joylife.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.joylife.tracker.common.ReData;
import top.joylife.tracker.common.bean.dto.OfferDto;
import top.joylife.tracker.common.bean.param.OfferParam;
import top.joylife.tracker.common.util.ReUtil;
import top.joylife.tracker.service.OfferService;

@RestController
@RequestMapping("offer")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @GetMapping(value = "/{id}")
    public ReData<OfferDto> getById(@PathVariable Integer id){
        OfferDto offerDto = offerService.getById(id);
        return ReUtil.success(offerDto);
    }

    @PutMapping(value = "/")
    public ReData<Integer> saveNetwork(@RequestBody OfferParam param){
        Integer id = offerService.saveOffer(param);
        return ReUtil.success(id);
    }

    @PostMapping(value = "/{id}")
    public ReData<Integer> updateNetwork(@PathVariable Integer id,
                                         @RequestBody OfferParam param){
        offerService.updateOffer(id,param);
        return ReUtil.success(id);
    }

    @DeleteMapping(value = "/{id}")
    public ReData<Integer> deleteNetwork(@PathVariable Integer id){
        offerService.deleteOffer(id);
        return ReUtil.success(id);
    }
}
