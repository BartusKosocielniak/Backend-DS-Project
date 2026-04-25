package com.koscielniak.projekt.controller;

import com.koscielniak.projekt.dto.User;
import com.koscielniak.projekt.services.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registerUser")
    public Map<String, String> register(@RequestBody User user) throws IOException, IOException {
        String status = userService.register(user);
        return Map.of("status", status);
    }
}