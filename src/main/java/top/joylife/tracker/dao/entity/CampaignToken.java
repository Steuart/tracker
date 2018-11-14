package top.joylife.tracker.dao.entity;

import javax.persistence.*;

@Table(name = "campaign_token")
public class CampaignToken {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "campaign_id")
    private Integer campaignId;

    private String name;

    private String value;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }
}