package com.aynur.stockbot.controller;

import com.aynur.stockbot.dto.RegisterRequest;
import com.aynur.stockbot.model.User;
import com.aynur.stockbot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public User registerUser(@RequestBody RegisterRequest registerRequest) {
                   return userService.register(registerRequest);
    }
}
