package com.example.demo.controller;

import com.example.demo.model.BasicUserDataInfo.BasicUserData;
import com.example.demo.model.UserReport;
import com.example.demo.repository.UserReportRepository;
import com.example.demo.service.UserReportService;
import com.example.demo.service.diseasesChecker.HypertensionCheckerService;
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
    HypertensionCheckerService hypertensionCheckerService;

    @Autowired
    UserReportService userReportService;

    @GetMapping("/userReport")
    public Iterable<UserReport> getInfo (){
        return userReportRepository.findAll();
    }

    @PostMapping("/userReport/{login}")
    public ResponseEntity<UserReport> addUserReport(@RequestBody UserReport userReport, @PathVariable String login){
        userReportService.addUserReport(login,userReport.getHypertension());
        return new ResponseEntity<UserReport>(HttpStatus.OK);
    }

}
