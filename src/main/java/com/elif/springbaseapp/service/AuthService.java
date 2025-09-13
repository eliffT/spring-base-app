package com.elif.springbaseapp.service;

import com.elif.springbaseapp.dto.request.RegisterRequest;
import com.elif.springbaseapp.dto.response.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest registerRequest);
}
