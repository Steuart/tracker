package top.joylife.tracker.dao.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "transfer_record")
@Data
public class TransferRecord extends BaseEntity{

    /**
     * 项目id
     */
    @Column(name = "campaign_id")
    private Integer campaignId;

    /**
     * 任务id
     */
    @Column(name = "traffic_id")
    private Integer trafficId;

    /**
     * 点击记录id
     */
    @Column(name = "click_record_id")
    private Integer clickRecordId;

    /**
     * 收入金额
     */
    private Integer earnings;

    /**
     * 支付金额
     */
    private Integer payout;

    /**
     * 转化时间
     */
    @Column(name = "transfer_date")
    private Date transferDate;

}