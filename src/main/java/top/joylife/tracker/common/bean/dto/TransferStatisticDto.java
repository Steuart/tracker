package top.joylife.tracker.common.bean.dto;

import lombok.Data;

@Data
public class TransferStatisticDto {

    private Integer todayTransfer;

    private Integer todayEarnings;

    private Integer monthEarnings;

    private Integer totalEarnings;
}
