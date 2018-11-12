package top.joylife.tracker.dao.entity;

import javax.persistence.*;

@Table(name = "campaign_landing_page")
public class CampaignLandingPage {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "campaign_id")
    private Integer campaignId;

    @Column(name = "landing_page_id")
    private Integer landingPageId;

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
     * @return landing_page_id
     */
    public Integer getLandingPageId() {
        return landingPageId;
    }

    /**
     * @param landingPageId
     */
    public void setLandingPageId(Integer landingPageId) {
        this.landingPageId = landingPageId;
    }
}