package com.security.authjpa.controller;

import com.security.authjpa.dto.UserDto;
import com.security.authjpa.model.User;
import com.security.authjpa.service.UserService;
import com.security.authjpa.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/register")
    public ApiResponse registerUser(@RequestBody UserDto user){

        return userService.saveUser(user);

    }

    @GetMapping("/list")
    public String getUserList(){
        return "Users";
    }
}
