package top.joylife.tracker.common.util;

import top.joylife.tracker.common.ReData;

public class ReUtil {

    /**
     * 成功返回
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ReData<T> success(T data){
        ReData<T> reData = new ReData<T>();
        reData.setCode("1");
        reData.setMsg("success");
        reData.setData(data);
        return reData;
    }


    /**
     * 失败返回
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ReData<T> fail(T data){
        ReData<T> reData = new ReData<T>();
        reData.setCode("0");
        reData.setMsg("fail");
        reData.setData(data);
        return reData;
    }

    /**
     * 失败返回
     * @param data
     * @param <T>
     * @return
     */
    public static  ReData fail(String code, String msg){
        ReData reData = new ReData();
        reData.setCode(code);
        reData.setMsg(msg);
        return reData;
    }

}
