package com.example.Storyloom.controller;

import com.example.Storyloom.dto.AuthResponse;
import com.example.Storyloom.dto.UserLoginRequest;
import com.example.Storyloom.dto.UserRegistrationRequest;
import com.example.Storyloom.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserLoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRegistrationRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }
}
