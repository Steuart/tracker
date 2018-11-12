package top.joylife.tracker.dao.entity;

import javax.persistence.*;

@Table(name = "campaign_offer")
public class CampaignOffer {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "campaign_id")
    private Integer campaignId;

    @Column(name = "offer_id")
    private Integer offerId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return campaign_id
     */
    public Integer getCampaignId() {
        return campaignId;
    }

    /**
     * @param campaignId
     */
    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    /**
     * @return offer_id
     */
    public Integer getOfferId() {
        return offerId;
    }

    /**
     * @param offerId
     */
    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }
}