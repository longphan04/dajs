package com.example.dacn.controllers;

import com.example.dacn.dtos.StatisticalDTO;
import com.example.dacn.services.StatisticalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("statistical")
@RequiredArgsConstructor
public class StatisticalController {
    private final StatisticalService statisticalService;

    @GetMapping
    public String index (Model model){
        StatisticalDTO statisticalDTO = new StatisticalDTO();
        statisticalDTO.setMonthly(true);
        statisticalDTO = statisticalService.getStatistical(statisticalDTO);
        model.addAttribute("statisticalDTO", statisticalDTO);
        return "statistical";
    }

    @PostMapping
    public String statistical(@ModelAttribute StatisticalDTO statisticalDTO, Model model) {
        statisticalDTO = statisticalService.getStatistical(statisticalDTO);
        model.addAttribute("statisticalDTO", statisticalDTO);
        return "statistical";
    }
}