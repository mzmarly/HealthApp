package com.example.demo.controller;

import com.example.demo.NutrionDataApi.NutritionDataApiController;
import com.example.demo.model.Nutrition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NutritionController {

    @Autowired
    NutritionDataApiController nutritionDataApiController;

    @PostMapping("/food/{login}/{foodName}/{portionWeight}")
    public ResponseEntity<Nutrition> addBasicUserInfo(@PathVariable String login, @PathVariable String foodName, @PathVariable double portionWeight){
        nutritionDataApiController.addNutritionByWeight(login,foodName,portionWeight);
        return new ResponseEntity<Nutrition>(HttpStatus.OK);
    }


    @DeleteMapping("/food/{id}")
    public void removeBasicUserData(@PathVariable long id) {
        nutritionDataApiController.removeFoodById(id);
    }

}
