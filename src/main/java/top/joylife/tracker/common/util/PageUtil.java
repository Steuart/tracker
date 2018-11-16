package top.joylife.tracker.common.util;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {


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
}
