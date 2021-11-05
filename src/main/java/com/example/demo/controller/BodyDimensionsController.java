package com.example.demo.controller;


import com.example.demo.model.BasicUserDataInfo.BasicUserData;
import com.example.demo.model.BodyDimensionsInfo.BodyDimensions;
import com.example.demo.repository.BodyDimensionsRepository;
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


    @Autowired
    BodyDimensionsRepository bodyDimensionsRepository;


    @GetMapping("/bodyDimensions")
    public Iterable<BodyDimensions> getInfo() {
        return bodyDimensionsRepository.findAll();
    }

    @PostMapping("/bodyDimensions/{login}")
    public ResponseEntity<BodyDimensions> addBasicUserData(@RequestBody BodyDimensions bodyDimensions,@PathVariable String login)
    {
        bodyDimensionsService.addBodyDimensions(login,bodyDimensions.getShoulders(),bodyDimensions.getWaist(), bodyDimensions.getHips());
        return new ResponseEntity<BodyDimensions>(HttpStatus.OK);

    }
    @DeleteMapping("/removeBodyDimensions/{id}")
    public ResponseEntity<HttpStatus> removeRating(@PathVariable(value = "id") long id) {
        bodyDimensionsService.removeBodyDimensions(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }

}
