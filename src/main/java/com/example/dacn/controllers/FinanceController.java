package com.example.dacn.controllers;

import com.example.dacn.dtos.DepositDTO;
import com.example.dacn.dtos.FinanceDTO;
import com.example.dacn.dtos.WithdrawDTO;
import com.example.dacn.services.DepositService;
import com.example.dacn.services.FinanceService;
import com.example.dacn.services.HomeService;
import com.example.dacn.services.WithdrawService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DecimalFormat;

@Controller
@RequestMapping("finance")
@RequiredArgsConstructor
public class FinanceController {
    private final DepositService depositService;
    private final WithdrawService withdrawService;
    private final FinanceService financeService;
    private final HomeService homeService;

    @GetMapping("deposit")
    public String depositIndex(Model model) {
        FinanceDTO financeDTO = financeService.initDepositDTO();
        String currentBalanceStr = new DecimalFormat("###,###,###").format(homeService.getCurrentBalance());

        model.addAttribute("financeDTO", financeDTO);
        model.addAttribute("currentBalance", currentBalanceStr);
        return "deposit";
    }

    @GetMapping("withdraw")
    public String withdrawIndex(Model model) {
        FinanceDTO financeDTO = financeService.initDepositDTO();
        String currentBalanceStr = new DecimalFormat("###,###,###").format(homeService.getCurrentBalance());

        model.addAttribute("financeDTO", financeDTO);
        model.addAttribute("currentBalance", currentBalanceStr);
        return "withdraw";
    }

    @PostMapping("deposit")
    public String deposit(@ModelAttribute DepositDTO depositDTO) {
        depositService.save(depositDTO);
        return "redirect:/home";
    }

    @PostMapping("withdraw")
    public String withdraw(@ModelAttribute WithdrawDTO withdrawDTO) {
        withdrawService.save(withdrawDTO);
        return "redirect:/home";
    }
}
