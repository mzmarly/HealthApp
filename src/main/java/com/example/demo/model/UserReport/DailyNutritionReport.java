package com.example.demo.model.UserReport;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "dailyNutritionReport")
@Data
public class DailyNutritionReport implements Comparable<DailyNutritionReport> {
    @Id
    @Column(name = "dailyNutritionReport_id")
    private long dailyNutritionReportId;

    @Column(name= "date")
    private LocalDate date;

    @Column(name = "calories")
    private double calories;

    @Column(name = "dailyServing_size_g")
    private double dailyServing_size_g;

    @Column(name = "dailyFat_total_g")
    private double dailyFat_total_g;

    @Column(name = "dailyFat_saturated_g")
    private double dailyFat_saturated_g;

    @Column(name = "dailyProtein_g")
    private double dailyProtein_g;

    @Column(name = "dailySodium_mg")
    private double dailySodium_mg;

    @Column(name = "dailyPotassium_mg")
    private double dailyPotassium_mg;

    @Column(name = "dailyCholesterol_mg")
    private double dailyCholesterol_mg;

    @Column(name = "dailyCarbohydrates_total_g")
    private double dailyCarbohydrates_total_g;

    @Column(name = "dailyFiber_g")
    private double dailyFiber_g;

    public DailyNutritionReport() {
    }

    @Column(name = "dailySugar_g")
    private double dailySugar_g;

    @Override
    public int compareTo(DailyNutritionReport o) {
        return Long.compare(this.getDailyNutritionReportId(),o.getDailyNutritionReportId());
    }
}
