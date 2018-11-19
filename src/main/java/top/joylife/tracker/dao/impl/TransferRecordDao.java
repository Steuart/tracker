package top.joylife.tracker.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;
import top.joylife.tracker.common.bean.dto.TransferCountDto;
import top.joylife.tracker.common.bean.query.BasePageQuery;
import top.joylife.tracker.common.util.DateUtil;
import top.joylife.tracker.dao.MyMapper;
import top.joylife.tracker.dao.entity.TransferRecord;
import top.joylife.tracker.dao.mapper.TransferRecordMapper;

import java.util.Date;

@Repository
public class TransferRecordDao extends BaseDao<TransferRecord> {

    @Autowired
    private TransferRecordMapper transferRecordMapper;

    @Override
    public MyMapper<TransferRecord> getMapper() {
        return transferRecordMapper;
    }

    /**
     * 构建分页查询条件
     *
     * @param pageQuery
     * @return
     */
    @Override
    public Example buildPageQueryExample(BasePageQuery pageQuery) {
        return null;
    }


    /**
     * 获取一段时间内的转化数
     * @param beginDate
     * @param endDate
     * @return
     */
    public TransferCountDto selectTransferCount(Date beginDate,Date endDate){
        String beginDateStr = null;
        if(beginDate!=null){
            beginDateStr = DateUtil.format(beginDate,DateUtil.NORM_DATETIME_PATTERN);
        }
        String endDateStr = null;
        if(endDate !=null ){
            endDateStr = DateUtil.format(endDate,DateUtil.NORM_DATETIME_PATTERN);
        }
        return transferRecordMapper.selectTransferCount(beginDateStr,endDateStr);
    }
}
