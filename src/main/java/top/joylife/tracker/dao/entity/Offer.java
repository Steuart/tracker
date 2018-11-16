package top.joylife.tracker.dao.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "offer")
@Data
public class Offer extends BaseEntity{

    /**
     * 联盟id
     */
    @Column(name = "network_id")
    private Integer networkId;

    /**
     * 名字
     */
    private String name;

    /**
     * url
     */
    private String url;

    /**
     * 支付金额
     */
    private Integer payout;
}