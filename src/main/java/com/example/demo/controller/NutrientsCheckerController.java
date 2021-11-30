package com.example.demo.controller;

import com.example.demo.model.NutrientsChecker.NutrientsChecker;
import com.example.demo.repository.NutrientsCheckerRepository;
import com.example.demo.service.NutrientsCheckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NutrientsCheckerController {

    @Autowired
    NutrientsCheckerService nutrientsCheckerService;

    @Autowired
    NutrientsCheckerRepository nutrientsCheckerRepository;

    @PostMapping("/nutrientsChecker/{login}")
    public ResponseEntity<NutrientsChecker> addBasicUserInfo(@PathVariable String login){
        nutrientsCheckerService.setStateFotNutrientsChecker(login);
        return new ResponseEntity<NutrientsChecker>(HttpStatus.OK);
    }

    @GetMapping("/nutrientsChecker")
    public Iterable<NutrientsChecker> getInfo() {
        return nutrientsCheckerRepository.findAll();
    }
}
