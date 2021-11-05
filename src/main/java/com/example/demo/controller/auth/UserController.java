package com.example.demo.controller.auth;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserController {

    public UserController() {
    }

    @Autowired
    UserService usersService;

    @Autowired
    UserRepository userRepository;


//    @GetMapping("/user/me")
//    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
//        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
//        return userSummary;
//    }

//    @GetMapping
//    public void get

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return usersService.getUsers();
    }


}
