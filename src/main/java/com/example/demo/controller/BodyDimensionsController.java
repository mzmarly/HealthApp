package com.example.demo.controller;

import com.example.demo.model.BodyDimensionsInfo.BodyDimensions;
import com.example.demo.service.BodyDimensionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BodyDimensionsController {

    @Autowired
    BodyDimensionsService bodyDimensionsService;

    @GetMapping("/bodyDimensions")
    public Iterable<BodyDimensions> getAllBodyDimensions() {
        return bodyDimensionsService.getAllBodyDimensions();
    }

    @GetMapping("/bodyDimensions/{login}")
    public Iterable<BodyDimensions> getBodyDimensionsForUser(@PathVariable String login) {
        return bodyDimensionsService.getBodyDimensionsByLogin(login);
    }

    @PostMapping("/bodyDimensions/{login}")
    public ResponseEntity<BodyDimensions> addBasicUserData(@RequestBody BodyDimensions bodyDimensions, @PathVariable String login) {
        bodyDimensionsService.addBodyDimensions(login, bodyDimensions.getShoulders(), bodyDimensions.getWaist(), bodyDimensions.getHips(), bodyDimensions.getWeight());
        return new ResponseEntity<BodyDimensions>(HttpStatus.OK);
    }

    @DeleteMapping("/removeBodyDimensions/{id}")
    public ResponseEntity<HttpStatus> removeBasicUserData(@PathVariable(value = "id") long id) {
        bodyDimensionsService.removeBodyDimensions(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/bodyDimensions/{login}/{day}/{month}/{year}")
    public Iterable<BodyDimensions> getBodyDimensionsForUser(@PathVariable String login, @PathVariable int day, @PathVariable int month, @PathVariable int year) {
        return bodyDimensionsService.getBodyDimensionsByLoginAndDate(login, day, month, year);
    }

    @GetMapping("/bodyDimensions/{login}/{month}")
    public Iterable<BodyDimensions> getBodyDimensionsForUserByMonth(@PathVariable String login, @PathVariable int month) {
        return bodyDimensionsService.getBodyDimensionsByLoginAndMonth(login, month);
    }

    @GetMapping("/bodyDimensions1/{login}")
    public double[] getMinMaxParams(@PathVariable String login) {
        return bodyDimensionsService.getMinMaxParams(login);
    }
}