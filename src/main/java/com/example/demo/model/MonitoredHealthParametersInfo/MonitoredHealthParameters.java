package com.example.demo.model.MonitoredHealthParametersInfo;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "monitoredHealthParameters")
@Data
public class MonitoredHealthParameters {
    @Id
    @Column(name = "monitoredHealthParameters_id")
    private long monitoredHealthParametersId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "weight")
    private double weight;

    public MonitoredHealthParameters(long monitoredHealthParametersId, LocalDate date, double weight, int systolicPressure, int diaSystolicPressure, int bloodSugarLevel) {
        this.monitoredHealthParametersId = monitoredHealthParametersId;
        this.date = date;
        this.weight = weight;
        this.systolicPressure = systolicPressure;
        this.diaSystolicPressure = diaSystolicPressure;
        this.bloodSugarLevel = bloodSugarLevel;
    }

    public MonitoredHealthParameters() {
    }

    @Column(name = "systolicPressure")
    private int systolicPressure; //skurczowe

    @Column(name = "diasystolicPressure")
    private int diaSystolicPressure; //rozkurczowe

    @Column(name = "bloodSugarLevel")
    private int bloodSugarLevel;
}
