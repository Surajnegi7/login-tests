package com.example.demo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @Test
    void testLoginUsingEnv() {
        String username = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");

        assertNotNull(username, "USERNAME env missing");
        assertNotNull(password, "PASSWORD env missing");

        Response resp = RestAssured.given()
                .contentType("application/json")
                .body("{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}")
                .post(baseUrl + "/login")
                .andReturn();

        // Expect 200 for valid creds; otherwise test fails
        assertEquals(200, resp.getStatusCode(), "expected HTTP 200 for valid creds");
        assertEquals("ok", resp.jsonPath().getString("status"));
        assertEquals(username, resp.jsonPath().getString("user"));
    }
}
