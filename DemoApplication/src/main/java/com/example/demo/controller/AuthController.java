package com.example.demo.controller;

import com.example.demo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) { this.authService = authService; }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        boolean ok = authService.checkCredentials(username, password);
        if (ok) {
            return ResponseEntity.ok(Map.of("status", "ok", "user", username));
        } else {
            return ResponseEntity.status(401).body(Map.of("status", "unauthorized"));
        }
    }
}
