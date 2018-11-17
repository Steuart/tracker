package top.joylife.tracker.common.bean.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TrafficDto {

    private Integer id;

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


    /**
     * 创建时间
     */
    private Date dateCreate;

    /**
     * 更新时间
     */
    private Date dateUpdate;
}
