package top.joylife.tracker.common.bean.dto;

import lombok.Data;

@Data
public class TransferCountDto {

    /**
     * 总转化数
     */
    private Integer transferCount;

    /**
     * 总收入
     */
    private Integer sumEarnings;

    /**
     * 总支出
     */
    private Integer sumPayout;
}
