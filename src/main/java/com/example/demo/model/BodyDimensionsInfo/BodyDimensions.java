package com.example.demo.model.BodyDimensionsInfo;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bodyDimensions")
@Data
public class BodyDimensions {
    @Id
    @Column(name = "bodyDimensions_id")
    private long bodyDimensionsId;

    @Column(name = "date")
    private LocalDate date;

    public BodyDimensions() {
    }

    public BodyDimensions(long bodyDimensionsId, LocalDate date, double shoulders, double waist, double hips) {
        this.bodyDimensionsId = bodyDimensionsId;
        this.date = date;
        this.shoulders = shoulders;
        this.waist = waist;
        this.hips = hips;
    }

    @Column(name = "shoulders")
    private double shoulders;

    @Column(name = "waist")
    private double waist;

    @Column(name = "hips") //biodra
    private double hips;
}
