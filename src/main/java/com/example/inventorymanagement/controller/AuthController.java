package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.model.dto.authDTO.request.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class AuthController {

    @GetMapping("/login")
    public String showLogin() {
        return "/auth/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new RegisterRequest());
        return "/auth/register";
    }
}
