package top.joylife.tracker.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.joylife.tracker.common.ReData;
import top.joylife.tracker.common.bean.dto.ClickRecordDto;
import top.joylife.tracker.common.bean.query.ClickRecordPageQuery;
import top.joylife.tracker.common.util.ReUtil;
import top.joylife.tracker.service.ClickRecordService;

@Controller
@RequestMapping("clickRecord")
@Slf4j
public class ClickRecordController {

    @Autowired
    private ClickRecordService clickRecordService;

    /**
     * 分页获取点击记录
     * @param query
     * @return
     */
    @PostMapping(value = "/page")
    @ResponseBody
    public ReData<PageInfo<ClickRecordDto>> pageClickRecord(@RequestBody(required = false) ClickRecordPageQuery query){
        if(query==null){
            query = new ClickRecordPageQuery();
        }
        PageInfo<ClickRecordDto> clickRecordDtoPageInfo = clickRecordService.pageClickRecord(query);
        return ReUtil.success(clickRecordDtoPageInfo);
    }
}
