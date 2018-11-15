package top.joylife.tracker.common.bean.param;

import lombok.Data;

import java.util.Date;

@Data
public class TrafficParam {

    /**
     * 流量名字
     */
    private String name;

    /**
     * 是否使用token
     */
    private Integer useToken;

    /**
     * http请求方法
     */
    private String httpMethod;

    /**
     * 回调url
     */
    private String postbackUrl;

    /**
     * 创建时间
     */
    private Date dateCreate;

    /**
     * 更新时间
     */
    private Date dateUpdate;
}
