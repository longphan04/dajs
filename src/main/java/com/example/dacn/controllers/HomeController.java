package com.example.dacn.controllers;

import com.example.dacn.dtos.HomeDTO;
import com.example.dacn.services.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"home"})
@RequiredArgsConstructor
public class HomeController {
    private final HomeService homeService;

    @GetMapping
    public String home(Model model) {
        HomeDTO homeDTO = homeService.loadHomeData();
        model.addAttribute("homeDTO", homeDTO);
        return "home";
    }
}
