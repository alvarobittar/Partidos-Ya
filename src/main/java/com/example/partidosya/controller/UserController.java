package com.example.partidosya.controller;

import com.example.partidosya.models.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @RequestMapping(value = "USER/{id}")
    public User getUser(@PathVariable Long id) {
        User user = new User();
        user.setId(id);
        user.setName("Juan");
        user.setApellido("Perez");
        user.setEmail("juanperez@gmail.com");
        user.setPassword("1234");
        return user;


    }

    @RequestMapping(value = "USER876")
    public User putUser() {
        User user = new User();
        user.setName("Juan");
        user.setApellido("Perez");
        user.setEmail("juanperez@gmail.com");
        user.setPassword("1234");
        return user;
    }


    @RequestMapping(value = "USER234")
    public User deleteUser() {
        User user = new User();
        user.setName("Juan");
        user.setApellido("Perez");
        user.setEmail("juanperez@gmail.com");
        user.setPassword("1234");
        return user;
    }
}
