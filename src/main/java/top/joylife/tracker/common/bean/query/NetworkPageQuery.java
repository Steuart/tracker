package top.joylife.tracker.common.bean.query;

import lombok.Data;

import java.util.Date;

@Data
public class NetworkPageQuery extends BasePageQuery {

    /**
     * 名字
     */
    private String name;

    private Date beginDate;

    private Date endDate;
}
