package com.example.demo.controller;

import com.example.demo.NutrionDataApi.NutritionDataApi;
import com.example.demo.model.UserReport.DailyNutritionReport;
import com.example.demo.model.UserReport.UserReport;
import com.example.demo.service.UserReportService;
import com.example.demo.service.diseasesChecker.DiseasesCheckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserReportController {

    @Autowired
    DiseasesCheckerService diseasesCheckerService;

    @Autowired
    UserReportService userReportService;

    @Autowired
    NutritionDataApi nutritionDataApi;

    @GetMapping("/userReport")
    public Iterable<UserReport> getUserReports (){
     return userReportService.getUserReports();
    }
    @GetMapping("/userReport/{login}")
    public Iterable<UserReport> getUserReportsForUser (@PathVariable String login){
     return userReportService.getUserReportsForUser(login);
    }

    @PostMapping("/userReport/{login}")
    public ResponseEntity<UserReport> addUserReport(@RequestBody UserReport userReport, @PathVariable String login){
        userReportService.addUserReport(login,userReport.getHypertension(),userReport.getDiabetes());
        return new ResponseEntity<UserReport>(HttpStatus.OK);
    }
    @PostMapping("/sumUpNutrition/{login}")
    public ResponseEntity<DailyNutritionReport> sumUpNutrition(@PathVariable String login){
        nutritionDataApi.addSumUpNutrition(login);
        return new ResponseEntity<DailyNutritionReport>(HttpStatus.OK);
    }

    @PostMapping("/updateReport/{login}")
    public ResponseEntity<UserReport> addUserReport(@PathVariable String login){
        diseasesCheckerService.checkHypertension(login);
        diseasesCheckerService.chekDiabetes(login);
        diseasesCheckerService.checkBMI(login);
        diseasesCheckerService.checkWHR(login);
        return new ResponseEntity<UserReport>(HttpStatus.OK);
    }
}
