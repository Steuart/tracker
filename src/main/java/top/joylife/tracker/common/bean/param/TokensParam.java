package top.joylife.tracker.common.bean.param;

import lombok.Data;

@Data
public class TokensParam {

    /**
     * 业务id
     */
    private Integer idRef;

    /**
     * 类型，0-campaign,1-traffic,2-network
     */
    private Integer type;

    /**
     * 名字
     */
    private String name;

    /**
     * 值
     */
    private String value;
}
