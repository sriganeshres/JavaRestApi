package com.example.restapiapp.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.restapiapp.api.model.User;
import com.example.restapiapp.api.model.reqUser;
import com.example.restapiapp.service.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public User getUser(@RequestParam Integer id) {
        Optional<User> user = userService.getUser(id);
        if (user.isPresent()) {
            return (User) user.get();
        }
        return null;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> users = userService.getUsers();
        return users;
    }

    @PostMapping("/user")
    public User postAddUser(@RequestBody reqUser userReq) {
        Optional<User> user = userService.makeUser(userReq);
        if (user.isPresent()) {
            return (User) user.get();
        }
        return null;
    }

    @PutMapping("/user/{id}")
    public User updatUser(@PathVariable String id, @RequestBody reqUser userReq) {
        Optional<User> user = userService.updateUser(Integer.parseInt(id), userReq);
        if (user.isPresent()) {
            return (User) user.get();
        }
        return null;
    }

}