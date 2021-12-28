package com.example.demo.NutrionDataApi;

import com.example.demo.model.NutrientsChecker.NutrientsPreconditions;
import com.example.demo.model.Nutrtion.Nutrition;
import com.example.demo.model.UserReport.DailyNutritionReport;

public interface NutritionDataApi {
    String addNutritionByWeight(String login, String food, double portionWeight);

    void removeFoodById(Long id);

    void generateDailyReport(String login);

    DailyNutritionReport addSumUpNutrition(String login);

    DailyNutritionReport getDailySumUpByLogin(String login);

    Iterable<DailyNutritionReport> getDailySumUpListByLogin(String login);

    Iterable<Nutrition> geNutritionByLogin(String login);

}
