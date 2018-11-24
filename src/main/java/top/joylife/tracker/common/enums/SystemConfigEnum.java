package top.joylife.tracker.common.enums;

import lombok.Getter;
import top.joylife.tracker.dao.entity.SystemConfig;

@Getter
public enum SystemConfigEnum {
    DOMAIN("domain", "域名");

    private String name;

    private String desc;

    SystemConfigEnum(String name, String desc){
        this.name = name;
        this.desc = desc;
    }

}
