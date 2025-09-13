package com.elif.springbaseapp.controller;


import com.elif.springbaseapp.dto.request.RegisterRequest;
import com.elif.springbaseapp.dto.request.UserRequest;
import com.elif.springbaseapp.dto.response.RegisterResponse;
import com.elif.springbaseapp.dto.response.UserResponse;
import com.elif.springbaseapp.service.impl.AuthServiceImpl;
import com.elif.springbaseapp.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final AuthServiceImpl authService;
    private static final Logger logger =  LoggerFactory.getLogger(UserController.class);


    @PostMapping("/save")
    public UserResponse createUser(@Valid @RequestBody UserRequest user){
        logger.info("Created User");
        return userService.createUser(user);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request){
        logger.info("Registered User");
        RegisterResponse response = authService.register(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PutMapping("/update/{id}")
    public UserResponse updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest request){
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }


}
