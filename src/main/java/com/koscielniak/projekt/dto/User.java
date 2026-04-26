package com.koscielniak.projekt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class User {
    public String email;
    public String password;

    public User() {}
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}