package com.example.partidosya.controller;

import com.example.partidosya.dto.UserLoginRequest;
import com.example.partidosya.dto.UserRegistrationRequest;
import com.example.partidosya.models.User;
import com.example.partidosya.service.InvalidCredentialsException;
import com.example.partidosya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Find user by id
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    // Find all users
    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    // Update user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userService.updateUser(user);
    }

    // Delete user
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


}





