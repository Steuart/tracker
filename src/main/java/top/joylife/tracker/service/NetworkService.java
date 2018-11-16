package top.joylife.tracker.service;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.joylife.tracker.common.bean.dto.NetworkDto;
import top.joylife.tracker.common.bean.param.NetworkParam;
import top.joylife.tracker.common.bean.query.NetworkPageQuery;
import top.joylife.tracker.common.util.PageUtil;
import top.joylife.tracker.dao.entity.Network;
import top.joylife.tracker.dao.impl.NetworkDao;

@Service
public class NetworkService {

    @Autowired
    private NetworkDao networkDao;

    public NetworkDto getById(Integer id){
        Network network = networkDao.getById(id);
        NetworkDto networkDto = new NetworkDto();
        BeanUtils.copyProperties(network,networkDto);
        return networkDto;
    }

    public Integer saveNetwork(NetworkParam param){
        Network network = new Network();
        BeanUtils.copyProperties(param,network);
        networkDao.insert(network);
        return network.getId();
    }

    public void updateNetwork(Integer id,NetworkParam param){
        Network network = new Network();
        BeanUtils.copyProperties(param,network);
        network.setId(id);
        networkDao.updateById(network);
    }

    public void deleteNetwork(Integer id){
        networkDao.softDeleteById(id,Network.class);
    }

    public PageInfo<NetworkDto> pageQueryNetWork(NetworkPageQuery query){
        PageInfo<Network> pageInfo = networkDao.pageQuery(query);
        return PageUtil.copy(pageInfo,NetworkDto.class);
    }
}
