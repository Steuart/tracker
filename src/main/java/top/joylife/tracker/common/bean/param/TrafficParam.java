package top.joylife.tracker.common.bean.param;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TrafficParam {

    /**
     * 流量名字
     */
    private String name;

    /**
     * 主页
     */
    private String homePage;

    /**
     * 备注
     */
    private String remark;

    private List<TrafficTokenParam> tokens;

    /**
     * 创建时间
     */
    private Date dateCreate;

    /**
     * 更新时间
     */
    private Date dateUpdate;
}
