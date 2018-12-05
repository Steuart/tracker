package top.joylife.tracker.dao.entity;

import lombok.Data;
import lombok.Getter;

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
     * 收入金额
     */
    private BigDecimal earning;

    /**
     * 点击内容
     */
    private String clickContent;

    /**
     * 转化内容
     */
    private String transferContent;

    /**
     * 转化日期
     */
    private Date transferDate;

    /**
     * 状态，0-未转化，1-转化
     */
    private Integer status;

    @Getter
    public enum StatusEnum{
        NOT_TRANSFER(0,"未转化"),
        TRANSFER(1,"已转化");

        private Integer code;

        private String desc;
        StatusEnum(Integer code, String desc){
            this.code = code;
            this.desc = desc;
        }
    }
}