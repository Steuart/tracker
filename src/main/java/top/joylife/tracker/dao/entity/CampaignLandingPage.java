package top.joylife.tracker.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "campaign_landing_page")
@Data
public class CampaignLandingPage extends BaseEntity{


    @Column(name = "campaign_id")
    private Integer campaignId;

    @Column(name = "landing_page_id")
    private Integer landingPageId;
}