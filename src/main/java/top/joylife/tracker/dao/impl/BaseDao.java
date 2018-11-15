package top.joylife.tracker.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;
import top.joylife.tracker.common.bean.query.BasePageQuery;
import top.joylife.tracker.dao.MyMapper;

import java.util.List;

public abstract class BaseDao<T> {

    public abstract MyMapper<T> getMapper();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public T getById(Integer id){
        if(id==null){
            return null;
        }
        MyMapper<T> myMapper = getMapper();
        return myMapper.selectByPrimaryKey(id);
    }


    /**
     * 增加
     * @param t
     */
    public int insert(T t){
        MyMapper<T> myMapper = getMapper();
        return myMapper.insertSelective(t);
    }

    /**
     * 根据id更新
     * @param t
     * @return
     */
    public int updateById(T t){
        MyMapper<T> myMapper = getMapper();
        return myMapper.updateByPrimaryKeySelective(t);
    }

    /**
     * 硬删除
     * @param id
     */
    public int deleteById(Integer id){
        MyMapper<T> myMapper = getMapper();
        return myMapper.deleteByPrimaryKey(id);
    }

    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    public PageInfo<T> pageQuery(BasePageQuery pageQuery){
        Integer pageNo = pageQuery.getPageNo();
        Integer pageSize = pageQuery.getPageSize();
        if(pageNo==null || pageNo <0){
            pageNo = 1;
        }
        if(pageSize==null || pageSize <0){
            pageSize = 20;
        }
        MyMapper<T> myMapper = getMapper();
        Example example = buildPageQueryExample(pageQuery);
        PageHelper.startPage(pageNo,pageSize);
        List<T> list = myMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    /**
     * 构建分页查询条件
     * @return
     */
    public abstract Example buildPageQueryExample(BasePageQuery pageQuery);
}
