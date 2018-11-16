package top.joylife.tracker.common.bean.query;

import lombok.Data;

import java.util.Date;

@Data
public class ClickRecordPageQuery extends BasePageQuery{

    /**
     * trafficId
     */
    private Integer trafficId;

    /**
     * networkId
     */
    private Integer networkId;

    /**
     * offerId
     */
    private Integer offerId;

    /**
     * 项目id
     */
    private Integer campaignId;

    /**
     * 创建开始日期
     */
    private Date createBeginDate;

    /**
     * 创建结束日期
     */
    private Date createEndDate;

    /**
     * 更新开始时间
     */
    private Date updateBeginDate;

    /**
     * 更新结束时间
     */
    private Date updateEndDate;
}
