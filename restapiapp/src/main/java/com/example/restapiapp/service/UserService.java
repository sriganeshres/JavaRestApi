package com.example.restapiapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.restapiapp.api.model.User;
import com.example.restapiapp.api.model.reqUser;

@Service
public class UserService {

    private List<User> users;

    public UserService() {
        this.users = new ArrayList<>();
        this.users.add(new User(1, "Sri", 20, "sri@gmail.com"));
        this.users.add(new User(2, "Ganesh", 30, "Ganesh@gmail.com"));
        this.users.add(new User(3, "Kumar", 25, "kumar@gmail.com"));
    }

    public Optional<User> getUser(int id) {
        Optional<User> optional = Optional.empty();
        for (User user : users) {
            if (id == user.getId()) {
                optional = Optional.of(user);
                return optional;
            }
        }
        return optional;
    }

    public Optional<User> makeUser(reqUser requser) {
        Optional<User> optional = Optional.empty();
        for (User user : users) {
            if (requser.getName() == user.getName()) {
                return optional;
            }
        }
        User user = new User(this.users.size() + 1, requser.getName(), requser.getAge(), requser.getEmail());
        this.users.add(user);
        optional = Optional.of(user);
        return optional;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public Optional<User> updateUser(int id, reqUser user) {
        Optional<User> optional = Optional.empty();
        for (User user1 : users) {
            if (id == user1.getId()) {
                user1.setName(user.getName());
                user1.setAge(user.getAge());
                user1.setEmail(user.getEmail());
                optional = Optional.of(user1);
                return optional;
            }
        }
        return optional;
    }
}