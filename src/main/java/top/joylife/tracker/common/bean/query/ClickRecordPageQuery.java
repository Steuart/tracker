package top.joylife.tracker.common.bean.query;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

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
     * 状态
     */
    private List<Integer> status;

    /**
     * 创建开始日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createBeginDate;

    /**
     * 创建结束日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
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
