package com.example.dacn.services;

import com.example.dacn.dtos.StatisticalDTO;
import com.example.dacn.repositories.DepositRepository;
import com.example.dacn.repositories.WithdrawRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class StatisticalService {
    private final DepositRepository depositRepository;
    private final WithdrawRepository withdrawRepository;
    private final UserService userService;

    public StatisticalDTO getMonthlyStatistical(StatisticalDTO statisticalDTO) {
        String username = userService.getLoginUsername();
        int year = statisticalDTO.getYear();
        int month = statisticalDTO.getMonth();
        if (year == 0 || month == 0) {
            LocalDate today = Instant.now().atZone(ZoneId.systemDefault()).toLocalDate();
            year = today.getYear();
            month = today.getMonthValue();
        }

        Double depositAmount = depositRepository.getDepositsInMonth(username, year, month);
        statisticalDTO.setDepositAmount(depositAmount == null ? 0 : depositAmount);

        Double withdrawAmount = withdrawRepository.getWithdrawsInMonth(username, year, month);
        statisticalDTO.setWithdrawAmount(withdrawAmount == null ? 0 : withdrawAmount);

        Double sumAmount = statisticalDTO.getDepositAmount() - statisticalDTO.getWithdrawAmount();
        statisticalDTO.setSumAmount(sumAmount);

        statisticalDTO.setDateString(String.format("%d-%02d", year, month));

        return statisticalDTO;
    }

    public StatisticalDTO getAnnualStatistical(StatisticalDTO statisticalDTO){
        String username = userService.getLoginUsername();
        int year = statisticalDTO.getYear();
        if (year == 0) {
            LocalDate today = Instant.now().atZone(ZoneId.systemDefault()).toLocalDate();
            year = today.getYear();
        }

        Double depositAmount = depositRepository.getDepositsInYear(username, year);
        statisticalDTO.setDepositAmount(depositAmount == null ? 0 : depositAmount);

        Double withdrawAmount = withdrawRepository.getWithdrawsInYear(username, year);
        statisticalDTO.setWithdrawAmount(withdrawAmount == null ? 0 : withdrawAmount);

        Double sumAmount = statisticalDTO.getDepositAmount() - statisticalDTO.getWithdrawAmount();
        statisticalDTO.setSumAmount(sumAmount);

        statisticalDTO.setDateString(String.format("%d", year));

        return statisticalDTO;
    }

    public StatisticalDTO getStatistical(StatisticalDTO statisticalDTO) {
        if(statisticalDTO.isMonthly()) {
            return this.getMonthlyStatistical(statisticalDTO);
        } else {
            return this.getAnnualStatistical(statisticalDTO);
        }
    }
}
