package com.example.demo.service;

import com.example.demo.model.NutrientsChecker.NutrientsChecker;

public interface NutrientsCheckerService {

    NutrientsChecker setStateFotNutrientsChecker(String login);

    Iterable<NutrientsChecker> getAllNutrientsChecker();
}
