package top.joylife.tracker.dao.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "click_record")
@Data
public class ClickRecord extends BaseEntity{

    @Column(name = "uuid")
    private String uuid;

    /**
     * campaignId
     */
    @Column(name = "campaign_id")
    private Integer campaignId;

    /**
     * 流量源id
     */
    @Column(name = "traffic_id")
    private Integer trafficId;

    @Column(name = "network_id")
    private Integer networkId;

    @Column(name = "offer_id")
    private Integer offerId;

    /**
     * 支付金额
     */
    private BigDecimal payout;

    /**
     * 内容
     */
    private String content;
}