package top.joylife.tracker.common.bean.query;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TransferRecordPageQuery extends BasePageQuery {

    /**
     * 项目id
     */
    private Integer campaignId;

    /**
     * 任务id
     */
    private Integer trafficId;

    /**
     * 点击记录id
     */
    private Integer clickRecordId;

    /**
     * 转化时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date transferBeginDate;

    /**
     *
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date transferEndDate;
}
