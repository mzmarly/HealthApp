package com.example.demo.NutrionDataApi;

import com.example.demo.model.Nutrition;
import com.example.demo.model.NutritionJSON;
import com.example.demo.repository.NutritionDataRepository;
import com.example.demo.repository.UserRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Set;


@Service
@Slf4j
public class NutritionDataApiControllerImpl implements NutritionDataApiController {

    @Autowired
    NutritionDataRepository nutritionDataRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public String getFood(String login, String food) {
        String url = "https://api.api-ninjas.com/v1/nutrition?query={food}";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", "lVUYcIaQGBHY4gz40swH3g==9lQqFAvISh7QbLpL");
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                String.class,
                food
        );
        JsonToObject(login, response.getBody());
        log.info(response.getBody());
        return response.getBody();
    }

    @Override
    public String addFood(String login, String food) {
        getFood(login,food);
        return "lll";
    }

    @Override
    public void removeFoodById(Long id) {
        nutritionDataRepository.deleteById(id);
    }

    public void JsonToObject(String login, String JSONBody) {
        long nutritionId=nutritionDataRepository.findAll().size();
        var user = userRepository.findByLogin(login).orElseThrow();
        LocalDate date = LocalDate.now();
        String response = JSONBody;
        Gson gson = new Gson();
        NutritionJSON[] nutritionDTO = gson.fromJson(response, NutritionJSON[].class);
        System.out.println(nutritionDTO[0].getName());
        Nutrition nutrition=new Nutrition(nutritionId,
                date,
                nutritionDTO[0].getName(),
                nutritionDTO[0].getCalories(),
                nutritionDTO[0].getServing_size_g(),
                nutritionDTO[0].getFat_total_g(),
                nutritionDTO[0].getFat_saturated_g(),
                nutritionDTO[0].getProtein_g(),
                nutritionDTO[0].getSodium_mg(),
                nutritionDTO[0].getPotassium_mg(),
                nutritionDTO[0].getCholesterol_mg(),
                nutritionDTO[0].getCarbohydrates_total_g(),
                nutritionDTO[0].getFiber_g(),
                nutritionDTO[0].getSugar_g()
        );
        nutritionDataRepository.save(nutrition);
        Set<Nutrition> nutritionSet=user.getNutritions();
        nutritionSet.add(nutrition);
        user.setNutritions(nutritionSet);
        user.getNutritions().add(nutrition);
        userRepository.save(user);


    }
}
