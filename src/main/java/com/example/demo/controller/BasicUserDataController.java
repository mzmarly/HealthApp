package com.example.demo.controller;

import com.example.demo.NutrionDataApi.NutritionDataApiController;
import com.example.demo.model.BasicUserDataInfo.BasicUserData;
import com.example.demo.model.BasicUserDataInfo.PhysicalActivity;
import com.example.demo.repository.BasicUserDataRepository;
import com.example.demo.service.BasicUserDataService;
import com.example.demo.service.NutritionService;
import com.example.demo.webclient.NutritionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class BasicUserDataController {

    @Autowired
    BasicUserDataService basicUserDataService;

    @Autowired
    NutritionClient nutritionClient;

    @Autowired
    BasicUserDataRepository basicUserDataRepository;

    @Autowired
    NutritionService nutritionService;

    @Autowired
    NutritionDataApiController nutritionDataApiController;

    @GetMapping("/basicUserData")
        public Iterable<BasicUserData> getInfo (){
            return basicUserDataRepository.findAll();
        }

        //testy do Nutrition
    @GetMapping("/basicUserData1")
    public String getInfo1 () throws IOException, URISyntaxException {
       return nutritionDataApiController.addNutritionByWeight("miccid","orange",1 );
    }

    @PostMapping ("/basicUserData/{login}")
        public ResponseEntity <BasicUserData> addBasicUserInfo(@RequestBody BasicUserData basicUserData,@PathVariable String login){
        basicUserDataService.addBasicUserData(login,basicUserData.getAge(),basicUserData.getSex(),basicUserData.getPhysicalActivity(),basicUserData.getHeight());
        return new ResponseEntity<BasicUserData>(HttpStatus.OK);
    }
    @PostMapping ("/basicUserData/{login}/{physicalActivity}")
    public ResponseEntity <BasicUserData> updateBasicUserInfo(@PathVariable String login, @PathVariable PhysicalActivity physicalActivity){
        basicUserDataService.updateBasicUserDataPhysicalActivity(login, physicalActivity);
        return new ResponseEntity<BasicUserData>(HttpStatus.OK);
    }
    @DeleteMapping("/basicUserData/{id}")
    public void removeBasicUserData(@PathVariable long id) {
        basicUserDataService.removeBasicUserData(id);
    }


    @DeleteMapping("/removeBasicUserData/{id}")
    public ResponseEntity<HttpStatus> removeRating(@PathVariable(value = "id") long id) {
        basicUserDataService.removeBasicUserData(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }
}
