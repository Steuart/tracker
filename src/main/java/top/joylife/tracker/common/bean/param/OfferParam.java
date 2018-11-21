package top.joylife.tracker.common.bean.param;

import lombok.Data;

import java.util.Date;

@Data
public class OfferParam {

    /**
     * 联盟id
     */
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

    private Date dateCreate;

    private Date dateUpdate;
}
