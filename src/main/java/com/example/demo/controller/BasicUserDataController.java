package com.example.demo.controller;

import com.example.demo.model.BasicUserDataInfo.BasicUserData;
import com.example.demo.model.BasicUserDataInfo.PhysicalActivity;
import com.example.demo.service.BasicUserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BasicUserDataController {

    @Autowired
    BasicUserDataService basicUserDataService;

    @GetMapping("/basicUserData")
    public Iterable<BasicUserData> getInfo() {
        return basicUserDataService.getIAllUserData();
    }

    @PostMapping("/basicUserData/{login}")
    public ResponseEntity<BasicUserData> addBasicUserInfo(@RequestBody BasicUserData basicUserData, @PathVariable String login) {
        basicUserDataService.addBasicUserData(login, basicUserData.getAge(), basicUserData.getSex(), basicUserData.getPhysicalActivity(), basicUserData.getHeight());
        return new ResponseEntity<BasicUserData>(HttpStatus.OK);
    }

    @PostMapping("/basicUserData/{login}/{physicalActivity}")
    public ResponseEntity<BasicUserData> updateBasicUserInfo(@PathVariable String login, @PathVariable PhysicalActivity physicalActivity) {
        basicUserDataService.updateBasicUserDataPhysicalActivity(login, physicalActivity);
        return new ResponseEntity<BasicUserData>(HttpStatus.OK);
    }

    @DeleteMapping("/removeBasicUserData/{id}")
    public ResponseEntity<HttpStatus> removeRating(@PathVariable(value = "id") long id) {
        basicUserDataService.removeBasicUserData(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }
}