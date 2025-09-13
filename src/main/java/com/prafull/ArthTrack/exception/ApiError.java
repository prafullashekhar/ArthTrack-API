package com.prafull.ArthTrack.exception;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ApiError {
    private String message;
    private HttpStatus statusCode;
    private LocalDateTime timestamp;

    public ApiError(){
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(String message, HttpStatus statusCode) {
        this();
        this.message = message;
        this.statusCode = statusCode;
    }
}
