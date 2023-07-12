package com.security.authjpa.service;

import com.security.authjpa.dto.UserDto;
import com.security.authjpa.model.User;
import com.security.authjpa.utils.ApiResponse;

public interface UserService {
    ApiResponse saveUser(UserDto user);

    User findByUserName(String userName);
}
