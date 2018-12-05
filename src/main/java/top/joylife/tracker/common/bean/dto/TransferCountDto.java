package top.joylife.tracker.common.bean.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferCountDto {

    /**
     * 总转化数
     */
    private Integer transferCount;

    /**
     * 总收入
     */
    private BigDecimal sumEarnings;

    /**
     * 总支出
     */
    private BigDecimal sumPayout;
}
