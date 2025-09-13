package com.elif.springbaseapp.service.impl;

import com.elif.springbaseapp.dto.request.UserRequest;
import com.elif.springbaseapp.dto.response.UserResponse;
import com.elif.springbaseapp.entity.User;
import com.elif.springbaseapp.exception.UserNotFoundException;
import com.elif.springbaseapp.repository.UserRepository;
import com.elif.springbaseapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Logger logger =  LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    @Transactional
    // default REQUIRED:  Mevcut bir transaction varsa kendisine dahil eder. Eğer mevcut bir işlem yoksa, yeni bir işlem başlatır.
    public UserResponse createUser(UserRequest request) {
        User user = modelMapper.map(request, User.class);
        User createUser = userRepository.save(user);
        logger.info("Created User");
        return modelMapper.map(createUser, UserResponse.class);
    }

    @Override
    public UserResponse getUser(Long id) {
        User getUser = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found with the given ID."));
        UserResponse userResponse = modelMapper.map(getUser, UserResponse.class);
        return userResponse;
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long id, UserRequest request) {
        User userUpt = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found with the given ID."));
        userUpt.setFirstName(request.getFirstName());
        userUpt.setLastName(request.getLastName());
        userUpt.setEmail(request.getEmail());
        userRepository.save(userUpt);
        return modelMapper.map(userUpt, UserResponse.class);
    }

    /*  Propogation = REQUIRES_NEW:
        - Bu metod çağrıldığında her zaman yeni bir transaction başlatır, varsa üstteki transaction geçici olarak askıya alınır.
        - Yani bu metod kendi bağımsız transaction’ına sahiptir.
    */

    /*  Isolation = READ_COMMITTED:
        - Bu transaction sadece commit edilmiş verileri görecektir.
        - Dirty read yoktur; başkalarının commit etmediği değişiklikleri göremez.
        - Başka transaction’ların henüz commit etmediği bir kullanıcıyı görmez.
    */

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public void deleteUser(Long id) {
        User deleteUser = userRepository.findById(id).orElse(null);
        if (deleteUser != null) {
            userRepository.delete(deleteUser);
        }
    }
}
