package top.joylife.tracker.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "campaign_offer")
@Data
public class CampaignOffer extends BaseEntity{


    @Column(name = "campaign_id")
    private Integer campaignId;

    @Column(name = "offer_id")
    private Integer offerId;

}