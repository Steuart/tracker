package top.joylife.tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.joylife.tracker.common.bean.dto.TransferCountDto;
import top.joylife.tracker.common.bean.dto.TransferStatisticDto;
import top.joylife.tracker.common.util.DateUtil;
import top.joylife.tracker.dao.impl.ClickRecordDao;

import java.util.Date;

@Service
public class TransferStatisticService {


    @Autowired
    private ClickRecordDao clickRecordDao;

    /**
     * 默认转化统计
     * @return
     */
    public TransferStatisticDto defaultTransferStatistic(){
        TransferStatisticDto dto = new TransferStatisticDto();
        //今日转化统计
        Date now  = new Date();
        Date todayBeginDate = DateUtil.getDayBeginTime(now);
        Date todayEndDate = DateUtil.getDayEndTime(now);
        TransferCountDto todayCount = clickRecordDao.selectTransferCount(todayBeginDate, todayEndDate);
        //本月转化统计
        Date monthBeginDate = DateUtil.getFirstDayOfMonth(now);
        TransferCountDto monthCount = clickRecordDao.selectTransferCount(monthBeginDate,todayEndDate);
        //总转化统计
        TransferCountDto totalCount = clickRecordDao.selectTransferCount(null,null);
        dto.setMonthEarnings(monthCount.getSumEarnings());
        dto.setTodayEarnings(todayCount.getSumEarnings());
        dto.setTodayTransfer(todayCount.getTransferCount());
        dto.setTotalEarnings(totalCount.getSumEarnings());
        return dto;
    }


}
