package com.example.demo.model;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
//@Builder
@Table(name = "nutritions")
public class Nutrition {

    @Id
    @Column(name = "nutrition_id")
    private long nutritionId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "nutritionName")
    private String nutritionName;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Column(name = "calories")
    private double calories;

    public Nutrition(long nutritionId, LocalDate date,String nutritionName, double calories, double serving_size_g, double fat_total_g, double fat_saturated_g, double protein_g, double sodium_mg, double potassium_mg, double cholesterol_mg, double carbohydrates_total_g, double fiber_g, double sugar_g) {
        this.nutritionId = nutritionId;
        this.date=date;
        this.nutritionName = nutritionName;
        this.calories = calories;
        this.serving_size_g = serving_size_g;
        this.fat_total_g = fat_total_g;
        this.fat_saturated_g = fat_saturated_g;
        this.protein_g = protein_g;
        this.sodium_mg = sodium_mg;
        this.potassium_mg = potassium_mg;
        this.cholesterol_mg = cholesterol_mg;
        this.carbohydrates_total_g = carbohydrates_total_g;
        this.fiber_g = fiber_g;
        this.sugar_g = sugar_g;
    }

    public Nutrition() {
    }

    public long getNutritionId() {
        return nutritionId;
    }

    public void setNutritionId(long nutritionId) {
        this.nutritionId = nutritionId;
    }

    public String getNutritionName() {
        return nutritionName;
    }

    public void setNutritionName(String nutritionName) {
        this.nutritionName = nutritionName;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getServing_size_g() {
        return serving_size_g;
    }

    public void setServing_size_g(double serving_size_g) {
        this.serving_size_g = serving_size_g;
    }

    public double getFat_total_g() {
        return fat_total_g;
    }

    public void setFat_total_g(double fat_total_g) {
        this.fat_total_g = fat_total_g;
    }

    public double getFat_saturated_g() {
        return fat_saturated_g;
    }

    public void setFat_saturated_g(double fat_saturated_g) {
        this.fat_saturated_g = fat_saturated_g;
    }

    public double getProtein_g() {
        return protein_g;
    }

    public void setProtein_g(double protein_g) {
        this.protein_g = protein_g;
    }

    public double getSodium_mg() {
        return sodium_mg;
    }

    public void setSodium_mg(double sodium_mg) {
        this.sodium_mg = sodium_mg;
    }

    public double getPotassium_mg() {
        return potassium_mg;
    }

    public void setPotassium_mg(double potassium_mg) {
        this.potassium_mg = potassium_mg;
    }

    public double getCholesterol_mg() {
        return cholesterol_mg;
    }

    public void setCholesterol_mg(double cholesterol_mg) {
        this.cholesterol_mg = cholesterol_mg;
    }

    public double getCarbohydrates_total_g() {
        return carbohydrates_total_g;
    }

    public void setCarbohydrates_total_g(double carbohydrates_total_g) {
        this.carbohydrates_total_g = carbohydrates_total_g;
    }

    public double getFiber_g() {
        return fiber_g;
    }

    public void setFiber_g(double fiber_g) {
        this.fiber_g = fiber_g;
    }

    public double getSugar_g() {
        return sugar_g;
    }

    public void setSugar_g(double sugar_g) {
        this.sugar_g = sugar_g;
    }

    @Column(name = "serving_size_g")
    private double serving_size_g;

    @Column(name = "fat_total_g")
    private double fat_total_g;

    @Column(name = "fat_saturated_g")
    private double fat_saturated_g;

    @Column(name = "protein_g")
    private double protein_g;

    @Column(name = "sodium_mg")
    private double sodium_mg;

    @Column(name = "potassium_mg")
    private double potassium_mg;

    @Column(name = "cholesterol_mg")
    private double cholesterol_mg;

    @Column(name = "carbohydrates_total_g")
    private double carbohydrates_total_g;

    @Column(name = "fiber_g")
    private double fiber_g;

    @Column(name = "sugar_g")
    private double sugar_g;
}
