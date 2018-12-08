package top.joylife.tracker.common.bean.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OfferDto {

    private Integer id;

    /**
     * 联盟id
     */
    private Integer networkId;

    /**
     * 联盟名字
     */
    private String networkName;

    /**
     * 名字
     */
    private String name;

    /**
     * 状态
     */
    private Integer status;

    /**
     * url
     */
    private String url;

    /**
     * 支付金额
     */
    private BigDecimal payout;

    /**
     * 备注
     */
    private String remark;

    private Date dateCreate;

    private Date dateUpdate;
}
