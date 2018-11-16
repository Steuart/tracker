package top.joylife.tracker.service;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.joylife.tracker.common.bean.dto.OfferDto;
import top.joylife.tracker.common.bean.param.OfferParam;
import top.joylife.tracker.common.bean.query.OfferPageQuery;
import top.joylife.tracker.common.util.PageUtil;
import top.joylife.tracker.dao.entity.Offer;
import top.joylife.tracker.dao.impl.OfferDao;

@Service
public class OfferService {

    @Autowired
    private OfferDao offerDao;

    public OfferDto getById(Integer id){
        Offer network = offerDao.getById(id);
        OfferDto offerDto = new OfferDto();
        BeanUtils.copyProperties(network,offerDto);
        return offerDto;
    }

    public Integer saveOffer(OfferParam param){
        Offer offer = new Offer();
        BeanUtils.copyProperties(param,offer);
        offerDao.insert(offer);
        return offer.getId();
    }

    public void updateOffer(Integer id,OfferParam param){
        Offer offer = new Offer();
        BeanUtils.copyProperties(param,offer);
        offer.setId(id);
        offerDao.updateById(offer);
    }

    public void deleteOffer(Integer id){
        offerDao.softDeleteById(id,Offer.class);
    }

    public PageInfo<OfferDto> pageQueryOffer(OfferPageQuery query){
        PageInfo<Offer> pageInfo = offerDao.pageQuery(query);
        return PageUtil.copy(pageInfo,OfferDto.class);
    }
}
