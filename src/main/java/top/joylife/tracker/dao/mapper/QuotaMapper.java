package top.joylife.tracker.dao.mapper;

import top.joylife.tracker.dao.MyMapper;
import top.joylife.tracker.dao.entity.Quota;

public interface QuotaMapper extends MyMapper<Quota> {

    /**
     * 清空表
     */
    void truncateTable();
}