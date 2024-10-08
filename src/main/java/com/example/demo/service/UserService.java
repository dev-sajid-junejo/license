package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User getUserById(Long id);
    User getUserByUsername(String username);
    List<User> getAllUsers();
    boolean authenticateUser(String username, String password);
}
