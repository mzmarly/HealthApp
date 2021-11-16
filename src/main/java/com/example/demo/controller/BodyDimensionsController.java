package com.example.demo.controller;


import com.example.demo.model.BodyDimensionsInfo.BodyDimensions;
import com.example.demo.repository.BodyDimensionsRepository;
import com.example.demo.service.BasicUserDataService;
import com.example.demo.service.BodyDimensionsService;
import com.example.demo.service.diseasesChecker.DiseasesCheckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BodyDimensionsController {

    @Autowired
    BodyDimensionsService bodyDimensionsService;


    @Autowired
    BodyDimensionsRepository bodyDimensionsRepository;

    @Autowired
    BasicUserDataService basicUserDataService;
@Autowired
DiseasesCheckerService diseasesCheckerService;

    @GetMapping("/bodyDimensions")
    public Iterable<BodyDimensions> getInfo() {
//        System.out.println(bodyDimensionsService.getAverageByMonth("miccid","NOVEMBER"));
        //  double[]tab=bodyDimensionsService.getMinMaxParams("miccid");
   //     basicUserDataService.updateBasicUserDataPhysicalActivity("miccid", ACTIVE_EVERY_DAY);
        diseasesCheckerService.checkHypertension("miccid");
        System.out.println(bodyDimensionsService.calculateBMI("miccid"));
        return bodyDimensionsRepository.findAll();
    }

    @PostMapping("/bodyDimensions/{login}")
    public ResponseEntity<BodyDimensions> addBasicUserData(@RequestBody BodyDimensions bodyDimensions, @PathVariable String login) {
        bodyDimensionsService.addBodyDimensions(login, bodyDimensions.getShoulders(), bodyDimensions.getWaist(), bodyDimensions.getHips(),bodyDimensions.getWeight());
        return new ResponseEntity<BodyDimensions>(HttpStatus.OK);

    }

    @DeleteMapping("/removeBodyDimensions/{id}")
    public ResponseEntity<HttpStatus> removeRating(@PathVariable(value = "id") long id) {
        bodyDimensionsService.removeBodyDimensions(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }

}
