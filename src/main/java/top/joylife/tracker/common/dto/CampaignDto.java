package top.joylife.tracker.common.dto;


import lombok.Data;
import java.util.Date;

@Data
public class CampaignDto {

    private Integer id;

    /**
     * 分组id
     */
    private Integer groupId;

    /**
     * 流量id
     */
    private Integer trafficId;

    /**
     * 联盟id
     */
    private Integer networkId;

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
