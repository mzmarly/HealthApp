package com.example.demo.controller;

import com.example.demo.model.UserReport.UserReport;
import com.example.demo.repository.UserReportRepository;
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
    UserReportRepository userReportRepository;

    @Autowired
    DiseasesCheckerService diseasesCheckerService;

    @Autowired
    UserReportService userReportService;

    @GetMapping("/userReport")
    public Iterable<UserReport> getInfo (){
        return userReportRepository.findAll();
    }

    @PostMapping("/userReport/{login}")
    public ResponseEntity<UserReport> addUserReport(@RequestBody UserReport userReport, @PathVariable String login){
        userReportService.addUserReport(login,userReport.getHypertension(),userReport.getDiabetes());
        return new ResponseEntity<UserReport>(HttpStatus.OK);
    }

    @PostMapping("/udateRaport/{login}")
    public ResponseEntity<UserReport> addUserReport(@PathVariable String login){
        diseasesCheckerService.checkHypertension(login);
        diseasesCheckerService.chekDiabetes(login);
        diseasesCheckerService.checkBMI(login);
        diseasesCheckerService.checkWHR(login);
        return new ResponseEntity<UserReport>(HttpStatus.OK);
    }

}
