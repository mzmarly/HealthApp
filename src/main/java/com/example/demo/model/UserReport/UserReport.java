package com.example.demo.model.UserReport;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userReport")
@Data
public class UserReport {
    @Id
    @Column(name = "userReport_id")
    private long userReportId;

    public UserReport(long userReportId, HypertensionLevel hypertension,DiabetesLevel diabetes,double bmi,double whr) {
        this.userReportId = userReportId;
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

}
