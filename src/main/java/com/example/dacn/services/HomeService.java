package com.example.dacn.services;

import com.example.dacn.dtos.DepositDTO;
import com.example.dacn.dtos.HomeDTO;
import com.example.dacn.dtos.WithdrawDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeService {
    private final DepositService depositService;
    private final WithdrawService withdrawService;
    private final CategoryService categoryService;
    private final UserService userService;

    public HomeDTO loadHomeData() {
        Double currentMonthDepositAmount = depositService.getCurrentMonthDepositAmount();
        Double currentMonthWithdrawAmount = withdrawService.getCurrentMonthWithdrawAmount();

        String username = userService.getLoginUsername();
        List<HomeDTO.TopDepositDTO> topDepositsOfThisMonth = categoryService.getTopDepositsOfThisMonth(username, currentMonthDepositAmount);
        List<HomeDTO.TopWithdrawDTO> topWithdrawsOfThisMonth = categoryService.getTopWithdrawsOfThisMonth(username, currentMonthWithdrawAmount);

        List<DepositDTO> recentTransactions = depositService.getRecentTransactions();

        return HomeDTO.builder()
                .currentBalance(this.getCurrentBalance())
                .depositAmount(currentMonthDepositAmount)
                .withdrawAmount(currentMonthWithdrawAmount)
                .topDeposits(topDepositsOfThisMonth)
                .topWithdraws(topWithdrawsOfThisMonth)
                .recentTransactions(recentTransactions)
                .build();
    }

    public Double getCurrentBalance() {
        Double sumAllDeposit = depositService.getSumAllDepositOfCurrentUser();
        Double sumAllWithdraw = withdrawService.getSumAllWithdrawOfCurrentUser();

        return sumAllDeposit - sumAllWithdraw;
    }
}
