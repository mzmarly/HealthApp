package com.example.demo.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String usernameOrEmail;
    private String password;

}