package top.joylife.tracker.dao.mapper;

import org.apache.ibatis.annotations.Param;
import top.joylife.tracker.common.bean.dto.TransferCountDto;
import top.joylife.tracker.dao.MyMapper;
import top.joylife.tracker.dao.entity.ClickRecord;

public interface ClickRecordMapper extends MyMapper<ClickRecord> {

    /**
     * 获取统计数据
     * @param beginDate
     * @param endDate
     * @return
     */
    TransferCountDto selectTransferCount(@Param(value = "beginDate") String beginDate,
                                         @Param(value = "endDate") String endDate);
}