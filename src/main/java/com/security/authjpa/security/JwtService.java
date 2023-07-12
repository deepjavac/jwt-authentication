package com.security.authjpa.security;


import com.security.authjpa.model.User;
import com.security.authjpa.repository.UserRepository;
import com.security.authjpa.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JwtService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(JwtService.class);
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository authRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserService authService;


    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
//         logger.info("==== user name is === "+userName+"    and Password is ===="+userPassword);





        authenticate(userName, userPassword);
        boolean validatedIp = false;
        String newGeneratedToken = "";

        User user = authService.findByUserName(userName);

        if (user != null) {

            UserDetails userDetails = loadUserByUsername(userName);
            newGeneratedToken = jwtUtil.generateToken(userDetails);
        } else {
            throw new UsernameNotFoundException("User Not found: " + userName);
        }

        //  keycloakUserCreation(user, userPassword);
        return new JwtResponse(user, newGeneratedToken);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // UserModel user = userDao.findById(username).get();
        User user = authRepository.findByUserName(username);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPassword(),
                    getAuthority(user)
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }



    private Set getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }

    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}
