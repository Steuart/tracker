package top.joylife.tracker.common.bean.param;

import lombok.Data;

@Data
public class QuotaParam {

    /**
     * 分组id
     */
    private Integer groupId;

    /**
     * code
     */
    private String code;

    /**
     * 名字
     */
    private String name;

    /**
     * 备注
     */
    private String remark;
}
