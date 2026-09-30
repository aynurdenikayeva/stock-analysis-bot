package com.aynur.stockbot.service;

import com.aynur.stockbot.dto.AuthResponse;
import com.aynur.stockbot.dto.LoginRequest;
import com.aynur.stockbot.dto.RegisterRequest;
import com.aynur.stockbot.dto.RegisterResponse;
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

    public RegisterResponse register(
            RegisterRequest registerRequest
    ) {

        // 1. Email artıq mövcuddurmu yoxlayırıq
        if (userRepository.findByEmail(
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

        // 3. Şifrəni BCrypt ilə hash edirik
        user.setPassword(
                passwordEncoder.encode(
                        registerRequest.getPassword()
                )
        );

        // 4. Database-ə yazırıq
        User savedUser = userRepository.save(user);

        // 5. Password-u göndərmədən response qaytarırıq
        return new RegisterResponse(
                savedUser.getUsername(),
                savedUser.getEmail()
        );
    }

    public AuthResponse login(
            LoginRequest loginRequest
    ) {

        User user = userRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"
                        )
                );

        // Gələn password ilə database-dəki hash-i müqayisə edirik
        boolean matches = passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword()
        );

        if (!matches) {
            throw new RuntimeException(
                    "Invalid password"
            );
        }

        // JWT token yaradırıq
        String token =
                jwtService.generateToken(
                        user.getEmail()
                );

        // Token + user məlumatlarını qaytarırıq
        return new AuthResponse(
                token,
                user.getUsername(),
                user.getEmail()
        );
    }
}
