package top.joylife.tracker.common.enums;

import lombok.Getter;

@Getter
public enum  QuotaEnum {
    PAYOUT("payout","支付金额"),
    CLICK_RECORD_UUID("clickRecordUuid","访问记录ID");
    private String code;
    private String desc;

    QuotaEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }
}
