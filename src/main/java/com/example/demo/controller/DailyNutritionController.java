package com.example.demo.controller;

import com.example.demo.NutrionDataApi.NutritionDataApi;
import com.example.demo.model.UserReport.DailyNutritionReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DailyNutritionController {
    @Autowired
    NutritionDataApi nutritionDataApi;

    @GetMapping("/dailyNutritionList/{login}")
    public Iterable<DailyNutritionReport> getDailySumUpListByLogin(@PathVariable String login) {
        return nutritionDataApi.getDailySumUpListByLogin(login);
    }

    @GetMapping("/dailyNutrition/{login}")
    public DailyNutritionReport getDailySumUpByLogin(@PathVariable String login) {
        return nutritionDataApi.getDailySumUpByLogin(login);
    }
}
