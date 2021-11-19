package com.example.demo.repository;

import com.example.demo.model.UserReport.DailyNutritionReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyNutritionReportRepository extends JpaRepository<DailyNutritionReport,Long> {
}
