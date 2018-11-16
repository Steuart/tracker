package top.joylife.tracker.service;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.joylife.tracker.common.bean.dto.TrafficDto;
import top.joylife.tracker.common.bean.param.TrafficParam;
import top.joylife.tracker.common.bean.query.TrafficPageQuery;
import top.joylife.tracker.common.util.PageUtil;
import top.joylife.tracker.dao.entity.Traffic;
import top.joylife.tracker.dao.impl.TrafficDao;

@Service
public class TrafficService {

    @Autowired
    private TrafficDao trafficDao;

    public TrafficDto getById(Integer id){
        Traffic traffic = trafficDao.getById(id);
        TrafficDto trafficDto = new TrafficDto();
        BeanUtils.copyProperties(traffic,trafficDto);
        return trafficDto;
    }

    public Integer saveTraffic(TrafficParam param){
        Traffic traffic = new Traffic();
        BeanUtils.copyProperties(param,traffic);
        trafficDao.insert(traffic);
        return traffic.getId();
    }

    public void updateTraffic(Integer id,TrafficParam param){
        Traffic traffic = new Traffic();
        BeanUtils.copyProperties(param,traffic);
        traffic.setId(id);
        trafficDao.updateById(traffic);
    }

    public void deleteTraffic(Integer id){
        trafficDao.softDeleteById(id,Traffic.class);
    }

    public PageInfo<TrafficDto> pageQueryTraffic(TrafficPageQuery query){
        PageInfo<Traffic> pageInfo = trafficDao.pageQuery(query);
        return PageUtil.copy(pageInfo,TrafficDto.class);
    }
}
