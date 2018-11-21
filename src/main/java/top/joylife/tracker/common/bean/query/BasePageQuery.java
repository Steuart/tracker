package top.joylife.tracker.common.bean.query;

import lombok.Data;
import top.joylife.tracker.common.bean.dto.SortDto;

import java.util.List;

@Data
public class BasePageQuery {

    protected Integer pageNo;

    protected Integer pageSize;

    protected List<SortDto> sorts;
}
