package top.joylife.tracker.common.bean.dto;

import lombok.Data;

@Data
public class TokensDto {

    private Integer id;

    /**
     * 业务id
     */
    private Integer idRef;

    /**
     * 类型，0:campaign-traffic,1:campaign-network,2:traffic,3:network,4:network-callback
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
