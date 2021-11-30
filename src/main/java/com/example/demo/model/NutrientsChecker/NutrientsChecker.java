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
    public void setNutrientsCheckerId(long nutrientsCheckerId) {
        this.nutrientsCheckerId = nutrientsCheckerId;
    }

    public NutrientsChecker(long nutrientsCheckerId, NutrientsState caloriesState, NutrientsState proteinState, NutrientsState fatsState, NutrientsState cholesterolState, NutrientsState carbohydratesState) {
        this.nutrientsCheckerId = nutrientsCheckerId;
        this.caloriesState = caloriesState;
        this.proteinState = proteinState;
        this.fatsState = fatsState;
        this.cholesterolState = cholesterolState;
        this.carbohydratesState = carbohydratesState;
    }

    @Id
    @Column(name = "nutrientsChecker_id")
    private long nutrientsCheckerId;

    @Column(name="caloriesState")
    private NutrientsState caloriesState;

    @Override
    public String toString() {
        return "NutrientsChecker{" +
                "nutrientsCheckerId=" + nutrientsCheckerId +
                ", caloriesState=" + caloriesState +
                ", proteinState=" + proteinState +
                ", fatsState=" + fatsState +
                ", cholesterolState=" + cholesterolState +
                ", carbohydratesState=" + carbohydratesState +
                '}';
    }

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
