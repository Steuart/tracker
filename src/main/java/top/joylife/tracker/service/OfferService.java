package top.joylife.tracker.service;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.joylife.tracker.common.bean.dto.OfferDto;
import top.joylife.tracker.common.bean.param.OfferParam;
import top.joylife.tracker.common.bean.query.OfferPageQuery;
import top.joylife.tracker.common.util.BeanUtil;
import top.joylife.tracker.dao.entity.Network;
import top.joylife.tracker.dao.entity.Offer;
import top.joylife.tracker.dao.impl.NetworkDao;
import top.joylife.tracker.dao.impl.OfferDao;

import java.util.*;

@Service
public class OfferService {

    @Autowired
    private OfferDao offerDao;

    @Autowired
    private NetworkDao networkDao;

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
        PageInfo<OfferDto> offerDtoPageInfo = BeanUtil.copy(pageInfo,OfferDto.class);
        List<OfferDto> offerDtos = offerDtoPageInfo.getList();
        Set<Integer> networkIds = new HashSet<>();
        if(!CollectionUtils.isEmpty(offerDtos)){
            offerDtos.forEach(offerDto -> {
                networkIds.add(offerDto.getNetworkId());
            });
        }
        Map<Integer,Network> networkMap = BeanUtil.generateMap(networkIds,networkDao,Network.class);
        offerDtos.forEach(offerDto -> {
            Network network = networkMap.get(offerDto.getNetworkId());
            if(network == null){
                return;
            }
            offerDto.setNetworkName(network.getName());
        });
        return offerDtoPageInfo;
    }

    public List<OfferDto> listOffer(){
        List<Offer> offers = offerDao.list(Offer.class);
        List<OfferDto> offerDtos = null;
        if(!CollectionUtils.isEmpty(offers)){
            offerDtos = new ArrayList<>();
            for(Offer offer:offers){
                OfferDto offerDto = new OfferDto();
                BeanUtils.copyProperties(offer,offerDto);
                offerDtos.add(offerDto);
            }
        }
        return offerDtos;
    }
}
