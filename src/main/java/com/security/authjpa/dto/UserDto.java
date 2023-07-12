package com.security.authjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long userId;
    private String fullName;
    private String email;
    private String contactNo;
    private String userName;
    private String password;
    private List<Long> roles;
}
