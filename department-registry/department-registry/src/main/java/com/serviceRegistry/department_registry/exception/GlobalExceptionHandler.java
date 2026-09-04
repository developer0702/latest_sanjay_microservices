package com.serviceRegistry.department_registry.exception;

import com.serviceRegistry.department_registry.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> CustomExceptionHandler(ResourceNotFoundException ex){
        String message= ex.getMessage();
        ApiResponse apiResponse= new ApiResponse(message,true);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
