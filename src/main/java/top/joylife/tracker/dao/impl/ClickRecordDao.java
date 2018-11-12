package top.joylife.tracker.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import top.joylife.tracker.dao.entity.ClickRecord;
import top.joylife.tracker.dao.mapper.ClickRecordMapper;
import top.joylife.tracker.dao.MyMapper;

@Repository
public class ClickRecordDao extends BaseDao<ClickRecord>{

    @Autowired
    private ClickRecordMapper clickRecordMapper;

    @Override
    public MyMapper<ClickRecord> getMapper() {
        return clickRecordMapper;
    }
}
