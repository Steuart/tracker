package top.joylife.tracker.dao.impl;

import top.joylife.tracker.dao.MyMapper;

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

}
