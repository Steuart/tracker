package top.joylife.tracker.common.bean.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class SortDto {

    /**
     * 排序字段名
     */
    private String fieldName;

    /**
     * 排序类型
     */
    private String sortType;

    @Getter
    public enum SortTypeEnum{
        ASC("asc","正序"),
        DESC("desc","倒叙");

        private String code;
        private String desc;

        SortTypeEnum(String code,String desc){
            this.code = code;
            this.desc = desc;
        }
    }
}
