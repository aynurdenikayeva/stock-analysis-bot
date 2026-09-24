package com.aynur.stockbot.service;

import com.aynur.stockbot.dto.AuthResponse;
import com.aynur.stockbot.dto.LoginRequest;
import com.aynur.stockbot.dto.RegisterRequest;
import com.aynur.stockbot.model.User;
import com.aynur.stockbot.repository.UserRepository;
import com.aynur.stockbot.security.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
    public User register(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(// 1. Email artıq mövcuddurmu yoxlayırıq
                registerRequest.getEmail()
        ).isPresent()) {
            throw new RuntimeException(
                    "Email already exists"
            );
        }
        // 2. Yeni User yaradırıq
        User user = new User();
        user.setUsername(
                registerRequest.getUsername()
        );
        user.setEmail(
                registerRequest.getEmail()
        );
        user.setPassword(// 3. Şifrəni BCrypt ilə hash edirik
                passwordEncoder.encode(
                        registerRequest.getPassword()
                )
        );
        return userRepository.save(user); // 4. Database-ə yazırıq
    }
    public AuthResponse login(LoginRequest loginRequest) {
        User user = userRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );
        boolean matches = passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword()
        );
        if (!matches) {
            throw new RuntimeException("Invalid password");
        }
        String token =
                jwtService.generateToken(user.getEmail());
        return new AuthResponse(
                token,
                user.getUsername(),
                user.getEmail()
        );
    }
}