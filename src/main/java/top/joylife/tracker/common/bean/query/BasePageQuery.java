package top.joylife.tracker.common.bean.query;

import lombok.Data;

@Data
public class BasePageQuery {

    protected Integer pageNo;

    protected Integer pageSize;
}
