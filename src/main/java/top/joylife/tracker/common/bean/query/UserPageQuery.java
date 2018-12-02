package top.joylife.tracker.common.bean.query;

import lombok.Data;

import java.util.Date;

@Data
public class UserPageQuery extends BasePageQuery {

    private String username;

    private Date beginDate;

    private Date endDate;
}
