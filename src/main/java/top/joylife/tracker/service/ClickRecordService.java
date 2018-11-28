package top.joylife.tracker.service;

import com.alibaba.fastjson.JSON;
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
import java.util.HashMap;
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
    public void saveClickRecord(String uuid, HttpServletRequest request){
        Map<String,String[]> params = request.getParameterMap();
        ClickRecord record = new ClickRecord();
        record.setUuid(uuid);
        String content = JSON.toJSONString(params);
        record.setContent(content);
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
