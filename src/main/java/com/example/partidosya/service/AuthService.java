package com.example.partidosya.service;

import com.example.partidosya.dto.AuthResponse;
import com.example.partidosya.dto.UserLoginRequest;
import com.example.partidosya.dto.UserRegistrationRequest;
import com.example.partidosya.jwt.JwtService;
import com.example.partidosya.models.Role;
import com.example.partidosya.models.User;
import com.example.partidosya.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(UserLoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);

        // Suponiendo que UserDetails tiene un método para obtener el ID del usuario
        Long userId = ((User) user).getId(); // Cambia esto según tu implementación del UserDetails

        return AuthResponse.builder()
                .token(token)
                .userId(userId) // Incluye el ID del usuario
                .build();
    }

    public AuthResponse register(UserRegistrationRequest request) {

// Encriptar la contraseña
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
            .username(request.getUsername())
            .password(encodedPassword)
            .email(request.getEmail())
            .role(Role.USER)
            .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }
}
