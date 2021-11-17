package com.example.demo.NutrionDataApi;

public interface NutritionDataApiController {
    //String makeAPICallForNutritionData(String food) throws URISyntaxException, IOException;
    String getFood(String login, String food);
    String addFood(String login, String food);
    void removeFoodById(Long id);

}
