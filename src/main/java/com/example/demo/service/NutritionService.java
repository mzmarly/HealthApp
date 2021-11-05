package com.example.demo.service;

import com.example.demo.model.Nutrition;

import com.example.demo.webclient.NutritionClient;
import com.example.demo.webclient.dto.NutritionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Collections;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class NutritionService {
    String url = "https://jsonplaceholder.typicode.com/posts/{id}";
    private RestTemplate restTemplate=new RestTemplate();
    private final NutritionClient nutritionClient;
//    HttpHeaders headers = new HttpHeaders();
//headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//    headers.setContentType(MediaType.APPLICATION_JSON);
//    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//    public Nutrition getNutrition(){
//        restTemplate.getForObject()
//    }

    public Nutrition getFood(){
//        String response= nutritionClient.getFood("orange");
//        log.info(response);
        return nutritionClient.getFood("Orange");
    }

}