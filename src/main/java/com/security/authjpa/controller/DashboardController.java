package com.security.authjpa.controller;

import com.security.authjpa.config.Constants;
import com.security.authjpa.utils.ApiResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {


    @GetMapping("/employees")
    @PreAuthorize("hasAnyRole('" + Constants.ROLE_USER + "')")
    public String getEmployeeList(){

        return "Employee list";
    }
}
