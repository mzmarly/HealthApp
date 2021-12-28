package com.example.demo.service;

import com.example.demo.model.NutrientsChecker.NutrientsChecker;
import com.example.demo.model.NutrientsChecker.NutrientsPreconditions;

public interface NutrientsCheckerService {

    NutrientsChecker setStateFotNutrientsChecker(String login);

    Iterable<NutrientsChecker> getAllNutrientsChecker();

    Iterable<NutrientsChecker> getNutrientsCheckerByLogin(String login);

    NutrientsPreconditions addNutrientsPreconditions(String login);

    Iterable<NutrientsPreconditions> getNutrientsPreconditionsListByLogin(String login);
}
