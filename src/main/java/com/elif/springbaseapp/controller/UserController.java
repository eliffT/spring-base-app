package com.elif.springbaseapp.controller;


import com.elif.springbaseapp.dto.request.UserRequest;
import com.elif.springbaseapp.dto.response.UserResponse;
import com.elif.springbaseapp.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;


    @PostMapping("/save")
    public UserResponse createUser(@Valid @RequestBody UserRequest user){
        return userService.createUser(user);
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
