package com.example.demo.controller;

import com.example.demo.model.NutrientsChecker.NutrientsChecker;
import com.example.demo.model.NutrientsChecker.NutrientsPreconditions;
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

    @PostMapping("/nutrientsChecker/{login}")
    public ResponseEntity<NutrientsChecker> addNutrientsChecker(@PathVariable String login) {
        nutrientsCheckerService.setStateFotNutrientsChecker(login);
        return new ResponseEntity<NutrientsChecker>(HttpStatus.OK);
    }

    @PostMapping("/nutrientsPreconditions/{login}")
    public ResponseEntity<NutrientsPreconditions> addNutrientsPreconditions(@PathVariable String login) {
        nutrientsCheckerService.addNutrientsPreconditions(login);
        return new ResponseEntity<NutrientsPreconditions>(HttpStatus.OK);
    }

    @GetMapping("/nutrientsPreconditions/{login}")
    public Iterable<NutrientsPreconditions> getNutrientsPreconditionsForUser(@PathVariable String login) {
        return nutrientsCheckerService.getNutrientsPreconditionsListByLogin(login);
    }
    @GetMapping("/nutrientsPreconditions/{login}/{day}/{month}/{year}")
    public Iterable<NutrientsPreconditions> getNutrientsPreconditionsForUserByDate(@PathVariable String login, @PathVariable int day, @PathVariable int month, @PathVariable int year) {
        return nutrientsCheckerService.getNutrientsPreconditionsListByLoginAndDate(login,day,month,year);
    }

    @GetMapping("/nutrientsChecker")
    public Iterable<NutrientsChecker> getAllNutrientsChecker() {
        return nutrientsCheckerService.getAllNutrientsChecker();
    }

    @GetMapping("/nutrientsChecker/{login}")
    public Iterable<NutrientsChecker> getNutrientsCheckerForUser(@PathVariable String login) {
        return nutrientsCheckerService.getNutrientsCheckerByLogin(login);
    }
}