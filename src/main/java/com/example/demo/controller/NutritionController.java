package com.example.demo.controller;

import com.example.demo.NutrionDataApi.NutritionDataApiController;
import com.example.demo.model.BasicUserDataInfo.BasicUserData;
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

    @PostMapping("/food/{login}/{foodName}")
    public ResponseEntity<Nutrition> addBasicUserInfo(@PathVariable String login, @PathVariable String foodName){
        nutritionDataApiController.getFood(login,foodName);
        return new ResponseEntity<Nutrition>(HttpStatus.OK);
    }

    @DeleteMapping("/food/{id}")
    public void removeBasicUserData(@PathVariable long id) {
        nutritionDataApiController.removeFoodById(id);
    }

}
