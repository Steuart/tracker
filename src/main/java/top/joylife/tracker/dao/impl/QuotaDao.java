package top.joylife.tracker.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;
import top.joylife.tracker.common.bean.query.BasePageQuery;
import top.joylife.tracker.dao.MyMapper;
import top.joylife.tracker.dao.entity.Quota;
import top.joylife.tracker.dao.mapper.QuotaMapper;

import java.util.List;

@Repository
public class QuotaDao extends BaseDao<Quota> {

    @Autowired
    private QuotaMapper quotaMapper;

    @Override
    public MyMapper<Quota> getMapper() {
        return quotaMapper;
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
     * 查询所有的指标和分组
     * @return
     */
    public List<Quota> listAllQuota(){
        return quotaMapper.selectAll();
    }
}
