package com.example.demo.model.NutrientsChecker;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "nutrientsPreconditions")
@Data
public class NutrientsPreconditions implements Comparable<NutrientsPreconditions> {

    public NutrientsPreconditions(long nutrientsPreconditionsId, LocalDate date, double caloriesPreconditions, double proteinPreconditions, double fatsPreconditions, double cholesterolPreconditions, double carbohydratesPreconditions) {
        this.nutrientsPreconditionsId = nutrientsPreconditionsId;
        this.date = date;
        this.caloriesPreconditions = caloriesPreconditions;
        this.proteinPreconditions = proteinPreconditions;
        this.fatsPreconditions = fatsPreconditions;
        this.cholesterolPreconditions = cholesterolPreconditions;
        this.carbohydratesPreconditions = carbohydratesPreconditions;
    }

    @Id
    @Column(name = "nutrientsPreconditionsId")
    private long nutrientsPreconditionsId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "caloriesPreconditions")
    private double caloriesPreconditions;


    @Column(name = "proteinPreconditions")
    private double proteinPreconditions;

    @Column(name = "fatsPreconditions")
    private double fatsPreconditions;

    @Column(name = "cholesterolPreconditions")
    private double cholesterolPreconditions;

    @Column(name = "carbohydratesPreconditions")
    private double carbohydratesPreconditions;

    public NutrientsPreconditions() {
    }

    @Override
    public int compareTo(NutrientsPreconditions o) {
        return Long.compare(this.getNutrientsPreconditionsId(), o.getNutrientsPreconditionsId());
    }
}
