package com.example.Storyloom.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor     //no usa getter ni setter
@NoArgsConstructor
public class UserLoginRequest {
    private String username;
    private String password;
}
