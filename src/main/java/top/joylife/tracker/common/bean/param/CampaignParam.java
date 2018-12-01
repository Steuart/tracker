package top.joylife.tracker.common.bean.param;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CampaignParam {

    /**
     * 流量id
     */
    private Integer trafficId;

    /**
     * 联盟id
     */
    private Integer networkId;

    /**
     * 任务id
     */
    private Integer offerId;

    /**
     * 重定向链接
     */
    private String redirectLink;

    /**
     * 名字
     */
    private String name;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 转化数量
     */
    private Integer leads;

    /**
     * 点击数
     */
    private Integer clicks;

    /**
     * 每点击花费
     */
    private Integer costPerClick;

    /**
     * 平均每lead获利
     */
    private Integer payPerLead;

    /**
     * 是否激活
     */
    private Integer isActive;

    /**
     * tokens
     */
    List<TokensParam> tokens;

    /**
     * 创建时间
     */
    private Date dateCreate;

    /**
     * 更新时间
     */
    private Date dateUpdate;

}
