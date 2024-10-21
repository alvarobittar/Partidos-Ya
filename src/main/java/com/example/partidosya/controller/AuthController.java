package com.example.partidosya.controller;

import com.example.partidosya.dto.AuthResponse;
import com.example.partidosya.dto.UserLoginRequest;
import com.example.partidosya.dto.UserRegistrationRequest;
import com.example.partidosya.service.AuthService;
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
