package com.example.demo.model.NutrientsChecker;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nutrientsChecker")
@Data
public class NutrientsChecker {
    @Id
    @Column(name = "nutrientsChecker_id")
    private long nutrientsCheckerId;

    @Column(name="caloriesState")
    private NutrientsState caloriesState;

    @Column(name="proteinState")
    private NutrientsState proteinState;

    @Column(name="fatsState")
    private NutrientsState fatsState;

    @Column(name="cholesterolState")
    private NutrientsState cholesterolState;

    @Column(name="carbohydratesState")
    private NutrientsState carbohydratesState;

    public NutrientsChecker() {
    }
}
