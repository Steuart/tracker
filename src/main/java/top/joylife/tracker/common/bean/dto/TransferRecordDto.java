package top.joylife.tracker.common.bean.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TransferRecordDto {

    private Integer id;

    /**
     * 项目id
     */
    private Integer campaignId;

    private String campaignName;

    /**
     * 任务id
     */
    private Integer trafficId;

    private String trafficName;

    /**
     * 点击记录id
     */
    private Integer clickRecordId;

    /**
     * 收入金额
     */
    private Integer earnings;

    /**
     * 内容
     */
    private String content;

    /**
     * 转化时间
     */
    private Date transferDate;
}
