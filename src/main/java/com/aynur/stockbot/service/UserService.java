package com.aynur.stockbot.service;

import com.aynur.stockbot.dto.RegisterRequest;
import com.aynur.stockbot.model.User;
import com.aynur.stockbot.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest registerRequest) {
            User user = new User();
             user.setPassword(
                passwordEncoder.encode(
                        registerRequest.getPassword()
                )
        );
            user.setUsername(registerRequest.getUsername());
            user.setEmail(registerRequest.getEmail());
            user.setPassword(registerRequest.getPassword());
             return userRepository.save(user);
    }
}
