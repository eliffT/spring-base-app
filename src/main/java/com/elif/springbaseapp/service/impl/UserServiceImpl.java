package com.elif.springbaseapp.service.impl;

import com.elif.springbaseapp.dto.request.UserRequest;
import com.elif.springbaseapp.dto.response.UserResponse;
import com.elif.springbaseapp.entity.User;
import com.elif.springbaseapp.repository.UserRepository;
import com.elif.springbaseapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public UserResponse createUser(UserRequest request) {
        User user = modelMapper.map(request, User.class);
        User createUser = userRepository.save(user);
        return modelMapper.map(createUser,UserResponse.class);
    }

    @Override
    public UserResponse getUser(Long id) {
        User getUser = userRepository.findById(id).orElse(null);
        UserResponse userResponse = modelMapper.map(getUser, UserResponse.class);
        return userResponse;
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        User userUpt = userRepository.findById(id).orElse(null);
        userUpt.setFirstName(request.getFirstName());
        userUpt.setLastName(request.getLastName());
        userUpt.setEmail(request.getEmail());
        userRepository.save(userUpt);
        return modelMapper.map(userUpt,UserResponse.class);
    }

    @Override
    public void deleteUser(Long id) {
        User deleteUser = userRepository.findById(id).orElse(null);
        userRepository.delete(deleteUser);
    }
}
