package top.joylife.tracker.common.exception;


import top.joylife.tracker.common.ErrorCode;

public class Warning extends RuntimeException {


    public Warning(String code,String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Warning(String msg){
        super(msg);
        this.code = "-1";
        this.msg = msg;
    }

    public Warning(ErrorCode errorCode,Object data){
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
        this.data = data;
    }

    public Warning(ErrorCode errorCode){
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    private String code;

    private String msg;

    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
