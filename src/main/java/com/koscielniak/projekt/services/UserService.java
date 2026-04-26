package com.koscielniak.projekt.services;


import com.koscielniak.projekt.dto.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final ObjectMapper mapper = new ObjectMapper();
    private final String PATH = "data/users.json";

    @PostConstruct
    public void init() throws IOException {
        // Tworzymy katalog 'data' w głównym folderze projektu przy starcie
        Files.createDirectories(Paths.get("data"));
    }

    public List<User> readUsers() {
        File file = new File(PATH);
        if (!file.exists()) return new ArrayList<>();
        return mapper.readValue(file, new TypeReference<List<User>>() {});

        //stare
//        try {
//            return mapper.readValue(file, new TypeReference<List<User>>() {});
//        } catch (IOException e) {
//            return new ArrayList<>();
//        }
    }

    public String register(User user) throws IOException {
        List<User> users = readUsers();
        if (users.stream().anyMatch(u -> u.email.equals(user.email))) {
            return "exists";
        }
        users.add(user);
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(PATH), users);
        return "registered";
    }

    public User login(User user) {
        List<User> users = readUsers();
        return users.stream().filter(u -> u.email.equals(user.getEmail()) && u.password.equals(user.getPassword())).findFirst().orElse(null);
    }
}