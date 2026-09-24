package com.aynur.stockbot.controller;

import com.aynur.stockbot.dto.AuthResponse;
import com.aynur.stockbot.dto.LoginRequest;
import com.aynur.stockbot.dto.RegisterRequest;
import com.aynur.stockbot.model.User;
import com.aynur.stockbot.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest loginRequest
    ) {
        return userService.login(loginRequest);
    }
}
