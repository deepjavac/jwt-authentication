package com.security.authjpa.serviceImpl;

import com.security.authjpa.dto.UserDto;
import com.security.authjpa.model.User;
import com.security.authjpa.model.UserRole;
import com.security.authjpa.repository.RoleRepository;
import com.security.authjpa.repository.UserRepository;
import com.security.authjpa.service.UserService;
import com.security.authjpa.utils.ApiResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;



    @Override
    public ApiResponse saveUser(UserDto userDto) {

        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        ApiResponse response = new ApiResponse();
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<UserRole> roleList = roleRepository.findByRoleIdIn(userDto.getRoles());

        // roles.add(role.get());
        user.setRoles(roleList);
        user = userRepository.save(user);

        if(user != null){
            response.setStatus(HttpStatus.CREATED);
            response.setData(user);
            response.setMsg("User Added");
        }
        else{
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMsg("Something went wrong!");
        }
        return response;
    }

    @Override
    public User findByUserName(String userName) {

        User userDetails = userRepository.findByUserName(userName);
        return userDetails;
    }
}
