package com.example.partidosya.database;

import com.example.partidosya.models.User;

import java.util.List;

public interface UserDB {

    List <User> getUsers();

    User register(User user);

    User updateUser(User user);

    void deleteUser(Long id);

    User getUser(Long id);
}
