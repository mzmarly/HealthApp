package com.example.demo.model.UserReport;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "userReport")
@Data
public class UserReport {
    @Id
    @Column(name = "userReport_id")
    private long userReportId;

    @Column(name= "date")
    private LocalDate date;

    public UserReport(long userReportId, LocalDate date, HypertensionLevel hypertension, DiabetesLevel diabetes, double bmi, double whr) {
        this.userReportId = userReportId;
        this.date = date;
        this.hypertension = hypertension;
        this.diabetes = diabetes;
        this.bmi = bmi;
        this.whr = whr;

    }
    public UserReport() {
    }
    @Column(name = "hypertension")
    private HypertensionLevel hypertension;
    @Column(name = "diabetes")
    private DiabetesLevel diabetes;
    @Column(name = "BMI")
    private double bmi;
    @Column(name = "WHR")
    private double whr;

    @OneToMany
    Set<DailyNutritionReport> dailyNutritionReports=new HashSet<>();

    public Set<DailyNutritionReport> getDailyNutritionReports() {
        return dailyNutritionReports;
    }

    public void setDailyNutritionReports(Set<DailyNutritionReport> dailyNutritionReports) {
        this.dailyNutritionReports = dailyNutritionReports;
    }
}
