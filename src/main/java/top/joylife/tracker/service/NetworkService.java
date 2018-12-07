package top.joylife.tracker.service;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.joylife.tracker.common.bean.dto.NetworkDto;
import top.joylife.tracker.common.bean.dto.TokensDto;
import top.joylife.tracker.common.bean.param.NetworkParam;
import top.joylife.tracker.common.bean.param.TokensParam;
import top.joylife.tracker.common.bean.query.NetworkPageQuery;
import top.joylife.tracker.common.enums.SystemConfigEnum;
import top.joylife.tracker.common.util.BeanUtil;
import top.joylife.tracker.dao.entity.Network;
import top.joylife.tracker.dao.entity.SystemConfig;
import top.joylife.tracker.dao.entity.Tokens;
import top.joylife.tracker.dao.impl.NetworkDao;
import top.joylife.tracker.dao.impl.SystemConfigDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NetworkService {

    @Autowired
    private NetworkDao networkDao;

    @Autowired
    private TokensService tokensService;

    @Autowired
    private SystemConfigDao systemConfigDao;

    public NetworkDto getById(Integer id){
        Network network = networkDao.getById(id);
        NetworkDto networkDto = new NetworkDto();
        BeanUtils.copyProperties(network,networkDto);
        List<TokensDto> callbackTokens = tokensService.listByIdRefAndType(id,Tokens.TypeEnum.CALLBACK.getCode());
        networkDto.setCallbackTokens(callbackTokens);
        List<TokensDto> offerTokens = tokensService.listByIdRefAndType(id,Tokens.TypeEnum.OFFER.getCode());
        networkDto.setOfferTokens(offerTokens);
        return networkDto;
    }

    public Integer saveNetwork(NetworkParam param){
        Network network = new Network();
        BeanUtils.copyProperties(param,network);
        network.setCallbackUrl(generateCallbackUrl(param.getCallbackTokens()));
        networkDao.insert(network);

        //保存tokens
        Integer networkId = network.getId();
        List<TokensParam> offerTokens = param.getOfferTokens();
        if(!CollectionUtils.isEmpty(offerTokens)) {
            tokensService.batchAddTokens(offerTokens,networkId,Tokens.TypeEnum.OFFER);
        }
        List<TokensParam> callbackTokens = param.getCallbackTokens();
        if(!CollectionUtils.isEmpty(callbackTokens)){
            tokensService.batchAddTokens(callbackTokens,networkId,Tokens.TypeEnum.CALLBACK);
        }
        return network.getId();
    }

    public void updateNetwork(Integer id,NetworkParam param){
        Network network = new Network();
        BeanUtils.copyProperties(param,network);
        network.setCallbackUrl(generateCallbackUrl(param.getCallbackTokens()));
        network.setId(id);
        //更新tokens
        Integer networkId = network.getId();
        List<TokensParam> offerTokens = param.getOfferTokens();
        if(!CollectionUtils.isEmpty(offerTokens)) {
            tokensService.batchUpdateTokens(offerTokens,networkId,Tokens.TypeEnum.OFFER);
        }
        List<TokensParam> callbackTokens = param.getCallbackTokens();
        if(!CollectionUtils.isEmpty(callbackTokens)){
            tokensService.batchUpdateTokens(callbackTokens,networkId,Tokens.TypeEnum.CALLBACK);
        }
        networkDao.updateById(network);
    }

    public void deleteNetwork(Integer id){
        networkDao.softDeleteById(id,Network.class);
    }

    public PageInfo<NetworkDto> pageQueryNetWork(NetworkPageQuery query){
        PageInfo<Network> pageInfo = networkDao.pageQuery(query);
        PageInfo<NetworkDto> result = BeanUtil.copy(pageInfo,NetworkDto.class);
        List<NetworkDto> networkDtos = result.getList();
        if(CollectionUtils.isEmpty(networkDtos)){
           return result;
        }
        List<Integer> ids = new ArrayList<>();
        networkDtos.forEach(networkDto -> {
            ids.add(networkDto.getId());
        });
        Map<Integer,List<TokensDto>> callbackTokensDtoMap = tokensService.mapByIdRefsAndType(ids,Tokens.TypeEnum.CALLBACK.getCode());
        Map<Integer,List<TokensDto>> offerTokensDtoMap = tokensService.mapByIdRefsAndType(ids, Tokens.TypeEnum.OFFER.getCode());
        networkDtos.forEach(networkDto -> {
            Integer id = networkDto.getId();
            networkDto.setOfferTokens(offerTokensDtoMap.get(id));
            networkDto.setCallbackTokens(callbackTokensDtoMap.get(id));
        });
        return result;
    }

    /**
     * 查询网路联盟列表
     * @return
     */
    public List<NetworkDto> listNetwork(){
        List<Network> networks = networkDao.list(Network.class);
        List<NetworkDto> networkDtos = new ArrayList<>();
        if(!CollectionUtils.isEmpty(networks)){
            networks.forEach(network -> {
                NetworkDto networkDto = new NetworkDto();
                BeanUtils.copyProperties(network,networkDto);
                networkDtos.add(networkDto);
            });
        }
        return networkDtos;
    }

    /**
     * 生成回调链接
     * @return
     */
    private String generateCallbackUrl(List<TokensParam> callbackTokens){
        SystemConfig config = systemConfigDao.getByName(SystemConfigEnum.DOMAIN.getName());
        String domain = config.getValue();
        StringBuilder callbackUrl = new StringBuilder();
        callbackUrl.append(domain).append("/network/callback?");
        callbackTokens.forEach(tokensDto -> {
            callbackUrl.append(tokensDto.getName()).append("=").append(tokensDto.getValue()).append("&");
        });
        return callbackUrl.substring(0,callbackUrl.length()-1);
    }
}
