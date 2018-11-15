package top.joylife.tracker.service;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import top.joylife.tracker.common.bean.dto.ClickRecordDto;
import top.joylife.tracker.common.bean.param.ClickRecordParam;
import top.joylife.tracker.common.bean.query.ClickRecordPageQuery;
import top.joylife.tracker.common.util.PageUtil;
import top.joylife.tracker.dao.entity.ClickRecord;
import top.joylife.tracker.dao.impl.ClickRecordDao;

import javax.servlet.http.HttpServletRequest;

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


    /**
     * 分页获取记录
     * @param query
     * @return
     */
    public PageInfo<ClickRecordDto> pageClickRecord(ClickRecordPageQuery query){
        PageInfo<ClickRecord> clickRecordDtoPageInfo = clickRecordDao.pageQuery(query);
        return PageUtil.copy(clickRecordDtoPageInfo,ClickRecordDto.class);
    }
}
