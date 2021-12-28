package com.example.demo.repository;

import com.example.demo.model.NutrientsChecker.NutrientsPreconditions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutrientsPreconditionsRepository extends JpaRepository<NutrientsPreconditions, Long> {
}
