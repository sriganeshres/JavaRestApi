package com.example.restapiapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import com.example.restapiapp.api.model.User;
import com.example.restapiapp.api.model.reqUser;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final String NODE_SERVICE_URL = "http://localhost:3000/users";
    private final RestTemplate restTemplate;

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getUsers() {
        ResponseEntity<User[]> response = restTemplate.getForEntity(NODE_SERVICE_URL, User[].class);
        return Arrays.asList(response.getBody());
    }

    public Optional<User> getUser(int id) {
        try {
            ResponseEntity<User> response = restTemplate.getForEntity(NODE_SERVICE_URL + "/" + id, User.class);
            return Optional.ofNullable(response.getBody());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<User> makeUser(reqUser requser) {
        HttpEntity<reqUser> request = new HttpEntity<>(requser);
        try {
            ResponseEntity<User> response = restTemplate.postForEntity(NODE_SERVICE_URL, request, User.class);
            return Optional.ofNullable(response.getBody());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<User> updateUser(int id, reqUser user) {
        HttpEntity<reqUser> request = new HttpEntity<>(user);
        try {
            ResponseEntity<User> response = restTemplate.exchange(
                    NODE_SERVICE_URL + "/" + id,
                    HttpMethod.PUT,
                    request,
                    User.class);
            return Optional.ofNullable(response.getBody());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
