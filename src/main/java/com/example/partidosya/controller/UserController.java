package com.example.partidosya.controller;

import com.example.partidosya.database.UserDB;
import com.example.partidosya.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDB userDB;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userDB.getUser(id);
    }

    @GetMapping
    public List<User> getUsers() {
        return userDB.getUsers();
    }

    @PostMapping
    public User registerUser(@RequestBody User user) {
        return userDB.register(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userDB.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userDB.deleteUser(id);
    }
}