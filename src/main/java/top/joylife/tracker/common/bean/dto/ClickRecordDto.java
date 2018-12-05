package top.joylife.tracker.common.bean.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Data
public class ClickRecordDto {

    private Integer id;

    private String uuid;

    /**
     * campaignId
     */
    private Integer campaignId;

    /**
     * 任务名字
     */
    private String campaignName;

    /**
     * 流量源id
     */
    private Integer trafficId;

    /**
     * 流量源名字
     */
    private String trafficName;

    /**
     * 网络联盟id
     */
    private Integer networkId;

    /**
     * 网络联盟名字
     */
    private String networkName;

    /**
     * 任务id
     */
    private Integer offerId;

    /**
     * offer名字
     */
    private String offerName;

    /**
     * 收入
     */
    private BigDecimal earning;

    /**
     * 支付
     */
    private BigDecimal payout;

    /**
     * 内容
     */
    private Map<String,Object> clickContent;

    /**
     * 转化内容
     */
    private Map<String,Object> transferContent;

    /**
     * 转化日期
     */
    private Date transferDate;

    /**
     * 状态，0-未转化，1-转化
     */
    private Integer status;

    /**
     * 记录创建时间
     */
    private Date dateCreate;

    /**
     * 记录更新时间
     */
    private Date dateUpdate;
}
