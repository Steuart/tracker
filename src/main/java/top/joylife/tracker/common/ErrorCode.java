package top.joylife.tracker.common;

import lombok.Getter;

@Getter
public enum ErrorCode {
    system_error("-1","系统异常"),
    user_name_can_not_be_empity("1000","用户名不能为空"),
    username_wrong("1001","用户名或密码错误"),
    password_can_not_be_empty("1002","密码不能为空"),
    login_expired("1003","登录信息过期，请重新登录"),

    campaign_exists("2001", "有项目正在使用TOKEN"),
    traffic_exists("2002", "有流量平台正在使用TOKEN"),

    quota_is_not_empty("2003", "指标不为空格，不可删除"),
    quota_can_not_delete("2004","指标不可删除"),
    quota_code_duplicate("2005","指标code重复,请换个code重试"),



    end_error("1111","终极异常");


    private String code;
    private String msg;

    ErrorCode(String code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
