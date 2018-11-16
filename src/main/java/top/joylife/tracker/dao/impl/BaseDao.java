package top.joylife.tracker.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;
import top.joylife.tracker.common.bean.dto.SortDto;
import top.joylife.tracker.common.bean.query.BasePageQuery;
import top.joylife.tracker.dao.MyMapper;
import top.joylife.tracker.dao.entity.BaseEntity;

import java.util.Date;
import java.util.List;

@Slf4j
public abstract class BaseDao<T extends BaseEntity> {

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
     * 软删除
     * @param id
     * @return
     */
    public int softDeleteById(Integer id,Class<T> tClass){
        MyMapper<T> myMapper = getMapper();
        T ob = null;
        try {
            ob = tClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("软删除失败，id:{},Class:{}",id,tClass.getName());
        }
        if(ob==null){
            return 0;
        }
        ob.setDateDelete(new Date());
        ob.setId(id);
        return myMapper.updateByPrimaryKeySelective(ob);
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
        List<SortDto> sorts = pageQuery.getSorts();
        if(!CollectionUtils.isEmpty(sorts)){
            SortDto sortDto = sorts.get(0);
            Example.OrderBy orderBy = null;
            if(SortDto.SortTypeEnum.ASC.getCode().equals(sortDto.getSortType())){
                orderBy = example.orderBy(sortDto.getFieldName()).asc();
            }else{
                orderBy = example.orderBy(sortDto.getFieldName()).desc();
            }
            if(sorts.size()>1){
                for(int i = 1;i<sorts.size();i++){
                    buildSort(sorts.get(i),orderBy);
                }
            }
        }
        PageHelper.startPage(pageNo,pageSize);
        List<T> list = myMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    /**
     * 构建sort
     * @param sortDto
     * @param orderBy
     * @return
     */
    private void buildSort(SortDto sortDto,Example.OrderBy orderBy){
        if(SortDto.SortTypeEnum.ASC.getCode().equals(sortDto.getSortType())){
            orderBy.orderBy(sortDto.getFieldName()).asc();
        }else{
            orderBy.orderBy(sortDto.getFieldName()).desc();
        }
    }

    /**
     * 构建分页查询条件
     * @return
     */
    public abstract Example buildPageQueryExample(BasePageQuery pageQuery);
}
