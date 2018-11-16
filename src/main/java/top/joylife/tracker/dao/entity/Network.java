package top.joylife.tracker.dao.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "network")
public class Network extends BaseEntity {

    /**
     * 名字
     */
    private String name;

    /**
     * 域名
     */
    private String domain;

}