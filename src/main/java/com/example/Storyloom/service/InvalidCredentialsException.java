package com.example.Storyloom.service;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {

        super(message);
    }
}
