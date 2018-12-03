package top.joylife.tracker.common.bean.query;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class OfferPageQuery extends BasePageQuery {

    private Integer networkId;

    private String name;

    private Integer status;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date beginDate;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endDate;
}
