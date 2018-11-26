package top.joylife.tracker.common.bean.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class QuotaDto {

    private Integer id;

    private String code;

    private String name;

    private String remark;

    private Integer groupId;

    private Date dateCreate;

    private Date dateUpdate;

    private List<QuotaDto> children;
}
