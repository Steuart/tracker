package top.joylife.tracker.common.bean.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferStatisticDto {

    private Integer todayTransfer;

    private BigDecimal todayEarnings;

    private BigDecimal monthEarnings;

    private BigDecimal totalEarnings;
}
