package com.example.Storyloom.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequest {
    private String username;
    private String password;
    private String email;
}