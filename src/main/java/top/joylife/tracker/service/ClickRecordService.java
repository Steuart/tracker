package top.joylife.tracker.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import top.joylife.tracker.common.param.ClickRecordParam;
import top.joylife.tracker.dao.entity.ClickRecord;
import top.joylife.tracker.dao.impl.ClickRecordDao;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class ClickRecordService {

    @Autowired
    private ClickRecordDao clickRecordDao;

    /**
     * 保存点击记录
     * @param uuid
     * @param param
     */
    @Async
    public void saveClickRecord(String uuid, ClickRecordParam param, HttpServletRequest request){
        ClickRecord record = new ClickRecord();
        record.setId(uuid);
        BeanUtils.copyProperties(param,record);
        clickRecordDao.insert(record);
    }
}
