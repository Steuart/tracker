package top.joylife.tracker.common.bean.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CampaignDto {

    private Integer id;

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
     * 访问链接
     */
    private String url;

    /**
     * 跳转链接
     */
    private String redirectUrl;

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
     * 总收入
     */
    private BigDecimal payouts;

    /**
     * 总支出
     */
    private BigDecimal earnings;

    /**
     * 每点击花费
     */
    private String costPerClick;

    /**
     * 平均每lead获利
     */
    private String payPerLead;

    /**
     * 收益比率
     */
    private String roi;

    /**
     * 是否激活
     */
    private Integer isActive;

    /**
     * 创建时间
     */
    private Date dateCreate;

    /**
     * 更新时间
     */
    private Date dateUpdate;

    /**
     * 删除时间
     */
    private Date dateDelete;

}
