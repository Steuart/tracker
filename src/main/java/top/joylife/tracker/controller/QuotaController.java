package top.joylife.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.joylife.tracker.common.ReData;
import top.joylife.tracker.common.bean.dto.QuotaDto;
import top.joylife.tracker.common.bean.param.QuotaParam;
import top.joylife.tracker.common.exception.Warning;
import top.joylife.tracker.common.util.ReUtil;
import top.joylife.tracker.dao.entity.Quota;
import top.joylife.tracker.service.QuotaService;

import java.util.List;

@RestController
@RequestMapping("quota")
public class QuotaController {

    @Autowired
    private QuotaService quotaService;

    /**
     * 查询列表
     * @return
     */
    @GetMapping(value = "listAll")
    public ReData<List<QuotaDto>> listAll(){
        List<QuotaDto> quotaDtos = quotaService.listAll();
        return ReUtil.success(quotaDtos);
    }

    /**
     * 保存指标
     * @param param
     * @return
     */
    @PutMapping(value = "")
    public ReData<Integer> saveQuota(@RequestBody QuotaParam param){
        if(param.getType() == null){
            throw new Warning("指标类型不能为空");
        }
        if(Quota.TypeEnum.QOUTA.getCode().equals(param.getType()) && param.getGroupId()==null){
            throw new Warning("分组id不能为空");
        }
        Integer id = quotaService.saveQuota(param);
        return ReUtil.success(id);
    }

    /**
     * 根据id更新指标
     * @param id
     * @param param
     * @return
     */
    @PostMapping(value = "/{id}")
    public ReData<Integer> updateQuota(@PathVariable Integer id, @RequestBody QuotaParam param){
        quotaService.updateQuota(id,param);
        return ReUtil.success(id);
    }

    /**
     * 删除指标
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ReData<Integer> deleteQuota(@PathVariable Integer id){
        quotaService.deleteQuota(id);
        return ReUtil.success(id);
    }
}
