package com.example.demo.service;

import com.example.demo.model.BodyDimensionsInfo.BodyDimensions;
import com.example.demo.model.NutrientsChecker.NutrientsChecker;
import com.example.demo.model.NutrientsChecker.NutrientsPreconditions;

public interface NutrientsPreconditionsService {

    Iterable<NutrientsPreconditions> getNutrientsPreconditionsListByLogin(String login);

    NutrientsPreconditions addBodyDimensions(String login);

}
