package top.joylife.tracker.common.bean.param;

import lombok.Data;

@Data
public class TrafficTokenParam {

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
