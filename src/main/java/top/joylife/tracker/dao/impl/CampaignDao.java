package top.joylife.tracker.dao.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;
import top.joylife.tracker.common.bean.query.CampaignPageQuery;
import top.joylife.tracker.dao.entity.Campaign;
import top.joylife.tracker.dao.mapper.CampaignMapper;
import top.joylife.tracker.dao.MyMapper;

import java.util.List;

@Repository
public class CampaignDao extends BaseDao<Campaign>{

    @Autowired
    private CampaignMapper campaignMapper;

    @Override
    public MyMapper<Campaign> getMapper() {
        return campaignMapper;
    }

    public PageInfo<Campaign> pageCampaign(CampaignPageQuery query){
        Integer pageNo = query.getPageNo();
        Integer pageSize = query.getPageSize();
        if(pageNo==null || pageNo<1){

        }
        PageHelper.startPage(query.getPageNo(),query.getPageSize());
        Example example = new Example(Campaign.class);
        List<Campaign> campaignList = campaignMapper.selectByExample(example);
        return new PageInfo<>(campaignList);
    }
}
