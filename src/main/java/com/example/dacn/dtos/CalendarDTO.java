package com.example.dacn.dtos;


import lombok.Data;

import java.text.DecimalFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
public class CalendarDTO {
    private static final DecimalFormat FORMATTER = new DecimalFormat("###,###,###");

    private Double depositAmount;
    private Double withdrawAmount;
    private Double sumAmount;
    private Date date = Date.from(Instant.now());
    private Double currentDateBalance;
    private List<DepositDTO> currentDateTransactions;

    public String getDepositAmountStr() {
        return FORMATTER.format(depositAmount);
    }

    public String getWithdrawAmountStr() {
        return FORMATTER.format(withdrawAmount);
    }

    public String getCurrentBalanceStr() {
        return FORMATTER.format(depositAmount - withdrawAmount);
    }

    public String getCurrentDateBalanceStr() {
        return FORMATTER.format(currentDateBalance);
    }
}
