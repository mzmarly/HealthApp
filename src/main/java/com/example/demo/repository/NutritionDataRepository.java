package com.example.demo.repository;

import com.example.demo.model.Nutrition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionDataRepository extends JpaRepository<Nutrition, Long> {
}
