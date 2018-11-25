package top.joylife.tracker.common.bean.query;

import lombok.Data;

import java.util.Date;

@Data
public class OfferPageQuery extends BasePageQuery {

    private Integer networkId;

    private String name;

    private Integer status;

    private Date beginDate;

    private Date endDate;
}
