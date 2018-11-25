package top.joylife.tracker.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import top.joylife.tracker.common.bean.query.BasePageQuery;
import top.joylife.tracker.common.bean.query.OfferPageQuery;
import top.joylife.tracker.dao.entity.Offer;
import top.joylife.tracker.dao.MyMapper;
import top.joylife.tracker.dao.mapper.OfferMapper;

@Repository
public class OfferDao extends BaseDao<Offer>{

    @Autowired
    private OfferMapper offerMapper;

    @Override
    public MyMapper<Offer> getMapper() {
        return offerMapper;
    }

    /**
     * 构建分页查询条件
     *
     * @param pageQuery
     * @return
     */
    @Override
    public Example buildPageQueryExample(BasePageQuery pageQuery) {
        OfferPageQuery query = (OfferPageQuery) pageQuery;
        Example example = new Example(Offer.class);
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(query.getName())){
            criteria.andLike("name","%"+query.getName()+"%");
        }
        criteria.andEqualTo("networkId",query.getNetworkId());
        criteria.andBetween("dateCreate",query.getBeginDate(),query.getEndDate());
        return example;
    }
}
