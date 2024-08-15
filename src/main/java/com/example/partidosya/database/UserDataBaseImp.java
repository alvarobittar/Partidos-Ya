package com.example.partidosya.database;

import com.example.partidosya.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDataBaseImp implements UserDB {

    private List<User> users = new ArrayList<>();

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User register(User user) {
        users.add(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        // Update logic
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        // Delete logic
    }

    @Override
    public User getUser(Long id) {
        // Get user logic
        return null;
    }
}
