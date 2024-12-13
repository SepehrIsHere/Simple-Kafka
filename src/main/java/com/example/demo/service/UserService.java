package com.example.demo.service;

import com.example.demo.entities.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User update(User user);

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAll();

    void delete(User user);

    void deleteById(long id);

    User findById(long id);

    boolean login(String username, String password);

    User register(User user);
}
