package com.elif.springbaseapp.service;

import com.elif.springbaseapp.entity.User;

public interface UserService {
    User createUser(User user);
    User getUser(Long id);
    User updateUser(Long id, User user);
    void deleteUser(Long id);

}
