package com.example.dacn.dtos;

import lombok.Builder;
import lombok.Data;

import java.text.DecimalFormat;
import java.util.List;

@Data
@Builder
public class HomeDTO {
    private static final DecimalFormat FORMATTER = new DecimalFormat("###,###,###");
    private Double currentBalance;
    private Double depositAmount;
    private Double withdrawAmount;
    private List<TopDepositDTO> topDeposits;
    private List<TopWithdrawDTO> topWithdraws;
    private List<DepositDTO> recentTransactions;

    public String getDifferenceAmountStr() {
        return FORMATTER.format(this.depositAmount - this.withdrawAmount);
    }

    public String getCurrentBalanceStr() {
        return FORMATTER.format(currentBalance);
    }

    public String getDepositAmountStr() {
        return FORMATTER.format(depositAmount);
    }

    public String getWithdrawAmountStr() {
        return FORMATTER.format(withdrawAmount);
    }

    @Data
    @Builder
    public static class TopDepositDTO {
        private Double amount;
        private String categoryName;
        private Double percent;

        public String getAmountStr() {
            return FORMATTER.format(amount);
        }

        public String getPercentStr() {
            return FORMATTER.format(percent);
        }
    }

    @Data
    @Builder
    public static class TopWithdrawDTO {
        private Double amount;
        private String categoryName;
        private Double percent;

        public String getAmountStr() {
            return FORMATTER.format(amount);
        }

        public String getPercentStr() {
            return FORMATTER.format(percent);
        }
    }

    @Data
    public static class RecentTransaction {
        private String type;
        private String categoryName;
        private String date;
        private Double amount;

        public String getAmountStr() {
            return FORMATTER.format(amount);
        }
    }
}
