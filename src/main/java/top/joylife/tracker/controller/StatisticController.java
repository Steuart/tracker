package top.joylife.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.joylife.tracker.common.ReData;
import top.joylife.tracker.common.bean.dto.TransferStatisticDto;
import top.joylife.tracker.common.util.ReUtil;
import top.joylife.tracker.service.TransferStatisticService;

/**
 * 统计信息
 */
@RestController
@RequestMapping(value = "statistic")
public class StatisticController {

    @Autowired
    private TransferStatisticService statisticService;

    /**
     * 获取默认的统计结果
     * @return
     */
    @GetMapping(value = "/default")
    public ReData<TransferStatisticDto> defaultTransferStatistic(){
        TransferStatisticDto result = statisticService.defaultTransferStatistic();
        return ReUtil.success(result);
    }
}
