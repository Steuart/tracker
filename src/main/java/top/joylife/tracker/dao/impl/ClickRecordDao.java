package top.joylife.tracker.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import top.joylife.tracker.common.bean.dto.TransferCountDto;
import top.joylife.tracker.common.bean.query.BasePageQuery;
import top.joylife.tracker.common.bean.query.ClickRecordPageQuery;
import top.joylife.tracker.common.util.DateUtil;
import top.joylife.tracker.dao.entity.ClickRecord;
import top.joylife.tracker.dao.mapper.ClickRecordMapper;
import top.joylife.tracker.dao.MyMapper;

import java.util.Date;
import java.util.List;

@Repository
public class ClickRecordDao extends BaseDao<ClickRecord>{

    @Autowired
    private ClickRecordMapper clickRecordMapper;

    @Override
    public MyMapper<ClickRecord> getMapper() {
        return clickRecordMapper;
    }

    /**
     * 构建分页查询条件
     *
     * @param pageQuery
     * @return
     */
    @Override
    public Example buildPageQueryExample(BasePageQuery pageQuery) {
        ClickRecordPageQuery query = (ClickRecordPageQuery) pageQuery;
        Example example = new Example(ClickRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("trafficId",query.getTrafficId())
                .andEqualTo("networkId",query.getNetworkId())
                .andEqualTo("offerId",query.getOfferId())
                .andEqualTo("campaignId",query.getCampaignId())
                .andBetween("dateCreate",query.getCreateBeginDate(),query.getCreateEndDate())
                .andBetween("dateUpdate",query.getUpdateBeginDate(),query.getUpdateEndDate());
        if(!CollectionUtils.isEmpty(query.getStatus())){
            criteria.andIn("status",query.getStatus());
        }
        return example;
    }

    /**
     * 根据uuid查询
     * @param uuid
     * @return
     */
    public ClickRecord getByUuid(String uuid){
        Example example = new Example(ClickRecord.class);
        example.createCriteria()
                .andEqualTo("uuid",uuid);
        return clickRecordMapper.selectOneByExample(example);
    }


    /**
     * 获取一段时间内的转化数
     * @param beginDate
     * @param endDate
     * @return
     */
    public TransferCountDto selectTransferCount(Date beginDate, Date endDate){
        String beginDateStr = null;
        if(beginDate!=null){
            beginDateStr = DateUtil.format(beginDate,DateUtil.NORM_DATETIME_PATTERN);
        }
        String endDateStr = null;
        if(endDate !=null ){
            endDateStr = DateUtil.format(endDate,DateUtil.NORM_DATETIME_PATTERN);
        }
        return clickRecordMapper.selectTransferCount(beginDateStr,endDateStr);
    }
}
