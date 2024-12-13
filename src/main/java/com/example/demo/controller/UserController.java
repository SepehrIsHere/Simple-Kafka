package com.example.demo.controller;

import com.example.demo.entities.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/save")
    public User save(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/update")
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    @GetMapping("/find-all")
    public List<User> getAll() {
        return userService.findAll();
    }

    @DeleteMapping
    public void delete(@RequestParam("id") Long id) {
        userService.deleteById(id);
    }
}
