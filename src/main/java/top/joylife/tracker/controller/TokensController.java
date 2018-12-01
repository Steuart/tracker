package top.joylife.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.joylife.tracker.common.ReData;
import top.joylife.tracker.common.bean.dto.TokensDto;
import top.joylife.tracker.common.bean.param.TokensParam;
import top.joylife.tracker.common.util.ReUtil;
import top.joylife.tracker.service.TokensService;

import java.util.List;

@RestController
@RequestMapping(value = "tokens")
public class TokensController {

    @Autowired
    private TokensService tokensService;

    /**
     * 保存tokens
     * @param tokensParam
     * @return
     */
    @PutMapping(value = "")
    public ReData<Integer> saveTokens(@RequestBody TokensParam tokensParam){
        Integer id = tokensService.saveTokens(tokensParam);
        return ReUtil.success(id);
    }

    /**
     * 更新trafficToken
     * @param id
     * @param param
     * @return
     */
    @PostMapping(value = "/{id}")
    public ReData<Integer> updateTokens(@PathVariable Integer id,
                                              @RequestBody TokensParam param){
        tokensService.updateTokens(id,param);
        return ReUtil.success(id);
    }

    /**
     * 删除trafficToken
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ReData<Integer> deleteTokens(@PathVariable Integer id){
        tokensService.deleteTokens(id);
        return ReUtil.success(id);
    }

    /**
     * 根据idRef获取type列表
     * @param idRef
     * @return
     */
    @GetMapping(value = "/list")
    public ReData<List<TokensDto>> listByIdRef(Integer idRef, Integer type){
        List<TokensDto> trafficTokenDtos = tokensService.listByIdRefAndType(idRef,type);
        return ReUtil.success(trafficTokenDtos);
    }

}
