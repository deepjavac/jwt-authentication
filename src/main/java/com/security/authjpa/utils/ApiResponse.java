package com.security.authjpa.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    private HttpStatus status;
    private String msg;
    private Object data;

    public ApiResponse(HttpStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ApiResponse(HttpStatus status, Object data) {
        this.status = status;
        this.data = data;
    }
}
