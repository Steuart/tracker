package top.joylife.tracker.common.bean.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransferRecordDto {

    private Integer id;

    /**
     * 项目id
     */
    private Integer campaignId;

    /**
     * 项目名字
     */
    private String campaignName;

    /**
     * 任务id
     */
    private Integer trafficId;

    /**
     * 流量名字
     */
    private String trafficName;

    /**
     * 点击记录id
     */
    private Integer clickRecordId;

    /**
     * 收入金额
     */
    private BigDecimal earnings;

    /**
     * 支付金额
     */
    private BigDecimal payout;

    /**
     * 内容
     */
    private String content;

    /**
     * 转化时间
     */
    private Date transferDate;
}
