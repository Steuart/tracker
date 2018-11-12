package top.joylife.tracker.dao.entity;

import javax.persistence.*;

@Table(name = "traffic_token")
public class TrafficToken {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String name;

    /**
     * 参数名
     */
    private String param;

    /**
     * 参数值
     */
    private String value;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取参数名
     *
     * @return param - 参数名
     */
    public String getParam() {
        return param;
    }

    /**
     * 设置参数名
     *
     * @param param 参数名
     */
    public void setParam(String param) {
        this.param = param;
    }

    /**
     * 获取参数值
     *
     * @return value - 参数值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置参数值
     *
     * @param value 参数值
     */
    public void setValue(String value) {
        this.value = value;
    }
}