package com.example.dacn.controllers;

import com.example.dacn.dtos.CalendarDTO;
import com.example.dacn.services.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("calendar")
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    @GetMapping
    public String index(Model model) {
        CalendarDTO calendarDTO = new CalendarDTO();
        calendarDTO = calendarService.getCalendarInfo(calendarDTO);
        model.addAttribute("calendarDTO", calendarDTO);
        return "calendar";
    }

    @PostMapping
    public String calendar(@ModelAttribute CalendarDTO calendarDTO, Model model) {
        calendarDTO = calendarService.getCalendarInfo(calendarDTO);
        model.addAttribute("calendarDTO", calendarDTO);
        return "calendar";
    }
}
