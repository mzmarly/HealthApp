package com.example.demo.NutrionDataApi;

import com.example.demo.model.UserReport.DailyNutritionReport;

public interface NutritionDataApi {
    String addNutritionByWeight(String login, String food, double portionWeight);

    void removeFoodById(Long id);

    void generateDailyReport(String login);

    DailyNutritionReport addSumUpNutrition(String login);

}
