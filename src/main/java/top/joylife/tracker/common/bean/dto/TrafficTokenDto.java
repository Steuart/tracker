package top.joylife.tracker.common.bean.dto;

import lombok.Data;

@Data
public class TrafficTokenDto {

    /**
     * id
     */
    private Integer id;

    /**
     * trafficId
     */
    private Integer trafficId;

    /**
     * 名字
     */
    private String name;

    /**
     * 参数值
     */
    private String value;

    /**
     * 备注
     */
    private String remark;
}
