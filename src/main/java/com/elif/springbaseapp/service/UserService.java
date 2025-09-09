package com.elif.springbaseapp.service;

import com.elif.springbaseapp.dto.request.UserRequest;
import com.elif.springbaseapp.dto.response.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest request);
    UserResponse getUser(Long id);
    UserResponse updateUser(Long id, UserRequest request);
    void deleteUser(Long id);
}
