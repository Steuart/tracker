package top.joylife.tracker.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;
import top.joylife.tracker.common.bean.query.BasePageQuery;
import top.joylife.tracker.dao.MyMapper;
import top.joylife.tracker.dao.entity.SystemConfig;
import top.joylife.tracker.dao.mapper.SystemConfigMapper;

@Repository
public class SystemConfigDao extends BaseDao<SystemConfig> {

    @Autowired
    private SystemConfigMapper systemConfigMapper;

    @Override
    public MyMapper<SystemConfig> getMapper() {
        return systemConfigMapper;
    }

    /**
     * 构建分页查询条件
     *
     * @param pageQuery
     * @return
     */
    @Override
    public Example buildPageQueryExample(BasePageQuery pageQuery) {
        return null;
    }

    /**
     * 根据名字查询系统配置
     * @param name
     * @return
     */
    public SystemConfig getByName(String name){
        Example example = new Example(SystemConfig.class);
        example.createCriteria()
                .andEqualTo("name", name);
        return systemConfigMapper.selectOneByExample(example);
    }
}
