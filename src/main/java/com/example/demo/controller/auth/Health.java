package com.example.demo.controller.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health {


    @GetMapping("/")
    public String home() {

        return "This app";
    }
}
