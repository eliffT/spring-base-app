package com.elif.springbaseapp.service.impl;

import com.elif.springbaseapp.entity.User;
import com.elif.springbaseapp.repository.UserRepository;
import com.elif.springbaseapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(Long id, User user) {
        User userUpt = userRepository.findById(id).orElse(null);
        userUpt.setFirstName(user.getFirstName());
        userUpt.setLastName(user.getLastName());
        userUpt.setEmail(user.getEmail());
        return userRepository.save(userUpt);
    }

    @Override
    public void deleteUser(Long id) {
        User deleteUser = userRepository.findById(id).orElse(null);
        userRepository.delete(deleteUser);
    }
}
