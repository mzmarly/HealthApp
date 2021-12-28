package com.example.demo.controller;

import com.example.demo.NutrionDataApi.NutritionDataApi;
import com.example.demo.model.NutrientsChecker.NutrientsChecker;
import com.example.demo.model.Nutrtion.Nutrition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NutritionController {

    @Autowired
    NutritionDataApi nutritionDataApi;

    @PostMapping("/food/{login}/{foodName}/{portionWeight}")
    public ResponseEntity<Nutrition> addBasicUserInfo(@PathVariable String login, @PathVariable String foodName, @PathVariable double portionWeight){
        nutritionDataApi.addNutritionByWeight(login,foodName,portionWeight);
        return new ResponseEntity<Nutrition>(HttpStatus.OK);
    }

    @DeleteMapping("/food/{id}")
    public void removeBasicUserData(@PathVariable long id) {
        nutritionDataApi.removeFoodById(id);
    }

    @GetMapping("/food/{login}")
    public Iterable<Nutrition> getNutritionForUser(@PathVariable String login) {
        return nutritionDataApi.geNutritionByLogin(login);
    }
}