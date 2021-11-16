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

    public UserReport(long userReportId, HypertensionLevel hypertension,DiabetesLevel diabetes) {
        this.userReportId = userReportId;
        this.hypertension = hypertension;
        this.diabetes = diabetes;

    }
    public UserReport() {
    }
    @Column(name = "hypertension")
    private HypertensionLevel hypertension;
    @Column(name = "diabetes")
    private DiabetesLevel diabetes;

}
