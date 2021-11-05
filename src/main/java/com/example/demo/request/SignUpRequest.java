package com.example.demo.request;

import lombok.Data;

@Data
public class SignUpRequest {

    private String nameSurname;

    private String login;

    private String email;

    private String password;


}
