package com.security.authjpa.controller;




import com.security.authjpa.security.JwtRequest;
import com.security.authjpa.security.JwtResponse;
import com.security.authjpa.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest, HttpServletRequest request) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
}
