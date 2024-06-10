package com.example.dacn.controllers;

import com.example.dacn.dtos.RegisterDTO;
import com.example.dacn.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @GetMapping
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("login")
    public String loginIndex() {
        return "login";
    }

    @GetMapping("register")
    public String registerIndex() {
        return "register";
    }

    @PostMapping("register")
    public String register(@ModelAttribute RegisterDTO registerDTO) {
        userService.createUser(registerDTO);
        return "login";
    }
}
