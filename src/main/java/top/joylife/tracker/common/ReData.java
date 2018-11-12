package top.joylife.tracker.common;

import lombok.Data;

@Data
public class ReData<T> {

    private String code;

    private String msg;

    private T data;
}
