package com.example.demo.NutrionDataApi;

import com.example.demo.model.UserReport.DailyNutritionReport;

public interface NutritionDataApiController {
    String addNutritionByWeight(String login, String food, double portionWeight);

    void removeFoodById(Long id);

    void generateDailyReport(String login);

    DailyNutritionReport addSumUpNutrition(String login);

}
