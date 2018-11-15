package top.joylife.tracker.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;
import top.joylife.tracker.common.bean.query.ClickRecordPageQuery;
import top.joylife.tracker.dao.entity.ClickRecord;
import top.joylife.tracker.dao.mapper.ClickRecordMapper;
import top.joylife.tracker.dao.MyMapper;

import java.util.List;

@Repository
public class ClickRecordDao extends BaseDao<ClickRecord>{

    @Autowired
    private ClickRecordMapper clickRecordMapper;

    @Override
    public MyMapper<ClickRecord> getMapper() {
        return clickRecordMapper;
    }

    public PageInfo<ClickRecord> pageCampaign(ClickRecordPageQuery query){
        Integer pageNo = query.getPageNo();
        Integer pageSize = query.getPageSize();
        if(pageNo==null || pageNo<1){
            pageNo = 1;
        }
        if(pageSize==null || pageSize<0){
            pageSize = 20;
        }
        PageHelper.startPage(pageNo,pageSize);
        Example example = new Example(ClickRecord.class);
        List<ClickRecord> campaignList = clickRecordMapper.selectByExample(example);
        return new PageInfo<>(campaignList);
    }
}
