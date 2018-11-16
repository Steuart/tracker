package top.joylife.tracker.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
public class BaseEntity {

    @Id
    @GeneratedValue(generator = "JDBC")
    protected Integer id;

    /**
     * 创建时间
     */
    @Column(name = "date_create")
    private Date dateCreate;

    /**
     * 更新时间
     */
    @Column(name = "date_update")
    private Date dateUpdate;

    /**
     * 删除时间
     */
    @Column(name = "date_delete")
    private Date dateDelete;
}
