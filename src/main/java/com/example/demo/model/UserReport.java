package com.example.demo.model;

import com.example.demo.model.HypertensionLevel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

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

    public UserReport(long userReportId, HypertensionLevel hypertension) {
        this.userReportId = userReportId;
        this.hypertension = hypertension;
    }
    public UserReport() {
    }
    @Column(name = "hypertension")
    private HypertensionLevel hypertension;

}
