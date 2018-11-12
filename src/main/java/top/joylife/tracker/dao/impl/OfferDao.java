package top.joylife.tracker.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
}
