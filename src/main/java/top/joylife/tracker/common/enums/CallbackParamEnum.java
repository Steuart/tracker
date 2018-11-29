package top.joylife.tracker.common.enums;

import lombok.Getter;

@Getter
public enum CallbackParamEnum {
    SUB_ID("subid","访问记录ID"),
    STATUS("status","状态"),
    PAYOUT("payout","支付金额");

    private String code;
    private String name;

    CallbackParamEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

}
