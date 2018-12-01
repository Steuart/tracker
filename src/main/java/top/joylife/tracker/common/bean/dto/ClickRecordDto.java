package top.joylife.tracker.common.bean.dto;

import lombok.Data;

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
     * 内容
     */
    private Map<String,Object> content;

    /**
     * 记录创建时间
     */
    private Date dateCreate;

    /**
     * 记录更新时间
     */
    private Date dateUpdate;
}
