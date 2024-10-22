package com.hw.spring_hw_ap.controller;

import com.hw.spring_hw_ap.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    private JwtUtils jwtUtils;

    // Эндпоинт для генерации JWT токена
    @GetMapping("/generateToken")
    public String generateToken(@RequestParam String username) {
        return jwtUtils.generateToken(username);
    }

    // Эндпоинт для проверки токена
    @GetMapping("/validateToken")
    public String validateToken(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);  // Убираем "Bearer " из начала токена
        if (jwtUtils.validateToken(jwt)) {
            return "Valid token for user: " + jwtUtils.getUsernameFromToken(jwt);
        } else {
            return "Invalid token";
        }
    }
}
