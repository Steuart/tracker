package top.joylife.tracker.common.util;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import top.joylife.tracker.common.enums.QuotaEnum;
import top.joylife.tracker.dao.entity.BaseEntity;
import top.joylife.tracker.dao.impl.BaseDao;

import java.util.*;

public class BeanUtil {


    /**
     * 拷贝pageInfo
     * @param fromPage
     * @param <F>
     * @param <T>
     */
    public static <F,T> PageInfo<T> copy(PageInfo<F> fromPage,Class<T> tClass){
        List<F> fromList = fromPage.getList();
        PageInfo<T> toPage = new PageInfo<>();
        if(CollectionUtils.isEmpty(fromList)){
            BeanUtils.copyProperties(fromPage,toPage);
            return toPage;
        }
        toPage.setPageNum(fromPage.getPageNum());
        toPage.setPageSize(fromPage.getPageSize());
        toPage.setSize(fromPage.getSize());
        toPage.setStartRow(fromPage.getStartRow());
        toPage.setEndRow(fromPage.getEndRow());
        toPage.setPages(fromPage.getPages());
        toPage.setPrePage(fromPage.getPrePage());
        toPage.setNextPage(fromPage.getNextPage());
        toPage.setIsFirstPage(fromPage.isIsFirstPage());
        toPage.setIsLastPage(fromPage.isIsLastPage());
        toPage.setHasNextPage(fromPage.isHasNextPage());
        toPage.setHasPreviousPage(fromPage.isHasPreviousPage());
        toPage.setNavigatePages(fromPage.getNavigatePages());
        toPage.setNavigatepageNums(fromPage.getNavigatepageNums());
        toPage.setNavigateFirstPage(fromPage.getNavigateFirstPage());
        toPage.setNavigateLastPage(fromPage.getNavigateLastPage());
        toPage.setTotal(fromPage.getTotal());
        List<T> list = new ArrayList<>();
        try {
            for (F f:fromList){
                T t = tClass.newInstance();
                BeanUtils.copyProperties(f,t);
                list.add(t);

            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        toPage.setList(list);
        return toPage;
    }

    /**
     * 生成map映射
     * @param ids
     * @param dao
     * @param t
     * @param <T>
     * @return
     */
    public static <T extends BaseEntity> Map<Integer,T> generateMap(Set<Integer> ids, BaseDao<T> dao, Class<T> t){
        List<T> lists = dao.listByIds(new ArrayList<>(ids),t);
        Map<Integer,T> result = new HashMap<>();
        for(T list:lists){
            result.put(list.getId(),list);
        }
        return result;
    }


    public static String getValueFromParams(QuotaEnum quotaEnum, Map<String,String[]> params) {
        String[] paramArr = params.get(quotaEnum.getCode());
        String param = null;
        if(paramArr.length>0){
            param = paramArr[0];
        }
        return param;
    }
}
