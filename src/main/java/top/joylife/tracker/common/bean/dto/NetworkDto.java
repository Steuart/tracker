package top.joylife.tracker.common.bean.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NetworkDto {
    private Integer id;

    /**
     * 名字
     */
    private String name;

    /**
     * 域名
     */
    private String domain;

    /**
     * 创建时间
     */
    private Date dateCreate;

    /**
     * 更新时间
     */
    private Date dateUpdate;

}
