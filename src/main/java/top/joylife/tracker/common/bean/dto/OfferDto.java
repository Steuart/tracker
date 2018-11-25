package top.joylife.tracker.common.bean.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OfferDto {

    private Integer id;

    /**
     * 联盟id
     */
    private Integer networkId;

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
    private Integer payout;

    /**
     * 备注
     */
    private String remark;

    private Date dateCreate;

    private Date dateUpdate;
}
