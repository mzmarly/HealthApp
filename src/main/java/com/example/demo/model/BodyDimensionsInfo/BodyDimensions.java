package com.example.demo.model.BodyDimensionsInfo;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bodyDimensions")
@Data
public class BodyDimensions implements Comparable<BodyDimensions> {
    @Id
    @Column(name = "bodyDimensions_id")
    private long bodyDimensionsId;

    @Column(name = "date")
    private LocalDate date;

    public BodyDimensions() {
    }

    public BodyDimensions(long bodyDimensionsId, LocalDate date, double shoulders, double waist, double hips,double weight) {
        this.bodyDimensionsId = bodyDimensionsId;
        this.date = date;
        this.shoulders = shoulders;
        this.waist = waist;
        this.hips = hips;
        this.weight = weight;
    }

    @Column(name = "shoulders")
    private double shoulders;

    @Column(name = "waist") //pas
    private double waist;

    @Column(name = "hips") //biodra
    private double hips;
    @Column(name = "weight")
    private double weight;

    @Override
    public int compareTo(BodyDimensions o) {
        return Long.compare(this.getBodyDimensionsId(), o.getBodyDimensionsId());
    }
}
