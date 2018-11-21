package top.joylife.tracker.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;
import top.joylife.tracker.common.bean.query.BasePageQuery;
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
        example.createCriteria()
                .andEqualTo("trafficId",query.getTrafficId())
                .andEqualTo("networkId",query.getNetworkId())
                .andEqualTo("offerId",query.getOfferId())
                .andEqualTo("campaignId",query.getCampaignId())
                .andBetween("dateCreate",query.getCreateBeginDate(),query.getCreateEndDate())
                .andBetween("dateUpdate",query.getUpdateBeginDate(),query.getUpdateEndDate());
        return example;
    }
}
