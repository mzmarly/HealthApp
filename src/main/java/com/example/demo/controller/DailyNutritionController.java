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

    @GetMapping("/dailyNutritionList/{login}/{day}/{month}/{year}")
    public Iterable<DailyNutritionReport> getDailySumUpListByLoginAndDate(@PathVariable String login, @PathVariable int day, @PathVariable int month, @PathVariable int year) {
        System.out.println("TESST");
        return nutritionDataApi.getDailySumUpListByLoginAndDate(login,day,month,year);
    }
    @GetMapping("/dailyNutrition/{login}")
    public DailyNutritionReport getDailySumUpByLogin(@PathVariable String login) {
        return nutritionDataApi.getDailySumUpByLogin(login);
    }
}
