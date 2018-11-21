package top.joylife.tracker.common.bean.query;

import lombok.Data;
import top.joylife.tracker.common.bean.dto.SortDto;

import java.util.Date;
import java.util.List;

@Data
public class CampaignPageQuery extends BasePageQuery{

    /**
     * 名字
     */
    private String name;

    /**
     * trafficId
     */
    private Integer trafficId;

    /**
     * networkId
     */
    private Integer networkId;

    /**
     * 创建开始日期
     */
    private Date beginDate;

    /**
     * 创建结束日期
     */
    private Date endDate;
}
