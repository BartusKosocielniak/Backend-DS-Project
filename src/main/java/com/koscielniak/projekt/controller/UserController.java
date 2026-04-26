package com.koscielniak.projekt.controller;

import com.koscielniak.projekt.dto.User;
import com.koscielniak.projekt.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
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

    @PostMapping("/loginUser")
    public Map<String, String> login(@RequestBody User user, HttpServletResponse response) {
        User found = userService.login(user);
        Map<String, String> res = new HashMap<>();

        if (found != null) {
            Cookie cookie = new Cookie("email", found.email);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(60);
            cookie.setPath("/");
            response.addCookie(cookie);

            res.put("email", found.email);

        } else {

            res.put("status", "notlogged");
        }
        return res;
    }

    @PostMapping("/logoutUser")
    public Map<String, String> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("email", null);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        Map<String, String> res = new HashMap<>();
        res.put("status", "logout");
        return res;
    }

    @GetMapping("/getCurrentUser")
    public Object getCurrent(@CookieValue(value = "email", required = false) String email) {
        if (email != null) {
            Map<String, String> res = new HashMap<>();
            res.put("email", email);
            return res;
        }
        Map<String, String> res = new HashMap<>();
        res.put("status", "unauthorized");
        return res;
    }
}