package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Very small in-memory credential check.
 * In the real app, replace with DB/auth service.
 */
@Service
public class AuthService {

    // sample users (never use real passwords in code)
    private final Map<String, String> users = Map.of(
            "alice", "alicepass",
            "bob", "bobpass",
            "carol", "carolpass"
    );

    public boolean checkCredentials(String username, String password) {
        if (username == null || password == null) return false;
        return password.equals(users.get(username));
    }
}
