package com.api_gateway.api_gatway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FallbackController {

    @GetMapping("/fallback/user")
    public ResponseEntity<Map<String, String>> userFallback() {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of(
                        "message", "User service is currently unavailable",
                        "status", "Service Unavailable"
                ));
    }

    @GetMapping("/fallback/department")
    public ResponseEntity<Map<String, String>> departmentFallback() {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of(
                        "message", "Department service is currently unavailable",
                        "status", "Service Unavailable"
                ));
    }
}