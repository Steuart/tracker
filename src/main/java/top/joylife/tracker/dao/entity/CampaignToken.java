package top.joylife.tracker.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "campaign_token")
@Data
public class CampaignToken extends BaseEntity{


    @Column(name = "campaign_id")
    private Integer campaignId;

    private String name;

    private String value;
}