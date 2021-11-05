package com.example.demo.webclient;

import com.example.demo.model.Nutrition;
import com.example.demo.webclient.dto.NutritionDto;
//import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Component
public class NutritionClient {
//    String url = "https://jsonplaceholder.typicode.com/posts/{id}";
//  private RestTemplate restTemplate=new RestTemplate();
//    HttpHeaders headers = new HttpHeaders();
//        headers.set("User-Agent", "eltabo");
//
//    public void testHeader( RestTemplate restTemplate){
//        //Set the headers you need send
//         HttpHeaders headers = new HttpHeaders();
//        headers.set("User-Agent", "eltabo");
//
//        //Create a new HttpEntity
//         HttpEntity<String> entity = new HttpEntity<String>(headers);
//
//        //Execute the method writing your HttpEntity to the request
//        ResponseEntity<Map> response = restTemplate.exchange("https://httpbin.org/user-agent", HttpMethod.GET, entity, Map.class);
//        System.out.println(response.getBody());

//    @Autowired
//    NutritionDataApiController nutritionDataApiController;

//    String food= "orange";
//    public ResponseEntity<String> getFood(String food) throws IOException, URISyntaxException {
//       return nutritionDataApiController.testByMichal(food);
//
//    }

    public Nutrition getFood(String food) {
//        String url = "https://api.api-ninjas.com/v1/nutrition?query={food}";
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.APPLICATION_JSON);
////        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        headers.set("X-Api-Key", "lVUYcIaQGBHY4gz40swH3g==9lQqFAvISh7QbLpL");
//        HttpEntity request = new HttpEntity(headers);
//        ResponseEntity<String> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                request,
//                String.class,
//                food
//        );
//        log.info(response.getBody());
        ResponseEntity<NutritionDto> nutritionDto= callGetMethod("Orange");
        return Nutrition.builder()
                .nutritionName(nutritionDto.getClass().getName())
                .build();
    }


    private <T> ResponseEntity<NutritionDto> callGetMethod(String food) {
        String url = "https://api.api-ninjas.com/v1/nutrition?query={food}";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Api-Key", "lVUYcIaQGBHY4gz40swH3g==9lQqFAvISh7QbLpL");
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<NutritionDto> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                NutritionDto.class,
                food
        );
        response.getClass();
//        return response.getBody();
        return response;
    }
    }
