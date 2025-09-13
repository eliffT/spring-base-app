package com.elif.springbaseapp.service.impl;

import com.elif.springbaseapp.dto.request.RegisterRequest;
import com.elif.springbaseapp.dto.response.RegisterResponse;
import com.elif.springbaseapp.entity.User;
import com.elif.springbaseapp.jwt.JwtService;
import com.elif.springbaseapp.repository.UserRepository;
import com.elif.springbaseapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        if(userRepository.findByUsername(registerRequest.getUsername()).isPresent()){
            throw new RuntimeException("Username already exists");
        }
        User user = modelMapper.map(registerRequest, User.class);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        User savedUser = userRepository.save(user);
        String token = jwtService.generateToken(savedUser);
        return new RegisterResponse("User registered successfully", token);

    }
}
