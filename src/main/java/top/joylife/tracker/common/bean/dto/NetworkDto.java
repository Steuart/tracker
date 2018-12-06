package top.joylife.tracker.common.bean.dto;

import lombok.Data;
import top.joylife.tracker.dao.entity.Tokens;

import java.util.Date;
import java.util.List;

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


    /**
     * 任务tokens
     */
    List<TokensDto> offerTokens;

    /**
     * 回调tokens
     */
    List<TokensDto> callbackTokens;

}
