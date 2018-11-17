package top.joylife.tracker.common.bean.param;

import lombok.Data;

import java.util.Date;

@Data
public class NetworkParam {

    /**
     * 名字
     */
    private String name;

    /**
     * 主页
     */
    private String homePage;

    /**
     * 回调url
     */
    private String callbackUrl;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date dateCreate;

    /**
     * 更新时间
     */
    private Date dateUpdate;
}
