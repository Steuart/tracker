package top.joylife.tracker.common.enums;

import lombok.Getter;

/**
 * 指标分组枚举值
 */
@Getter
public enum QuotaGroupEnum {
    HTTP("http","HTTP"),
    CLICK_DATA("clickData","点击数据");

    private String code;
    private String name;

    QuotaGroupEnum(String code,String name){
        this.code = code;
        this.name = name;
    }
}
