package com.example.demo.webclient.dto;

import lombok.Getter;

@Getter
public class NutritionDto {

    private String name;
    private double calories;
    private double serving_size_g;
    private double fat_total_g;
    private double fat_saturated_g;
    private double protein_g;
    private double sodium_mg;
    private double potassium_mg;
    private double cholesterol_mg;
    private double carbohydrates_total_g;
    private double fiber_g;
    private double sugar_g;
}
