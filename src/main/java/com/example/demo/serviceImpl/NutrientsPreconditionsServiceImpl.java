package com.example.demo.serviceImpl;

import com.example.demo.model.NutrientsChecker.NutrientsPreconditions;
import com.example.demo.model.UserReport.DailyNutritionReport;
import com.example.demo.repository.NutrientsPreconditionsRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.NutrientsPreconditionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class NutrientsPreconditionsServiceImpl implements NutrientsPreconditionsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    NutrientsPreconditionsRepository nutrientsPreconditionsRepository;

    public List<NutrientsPreconditions> getDailyNutritionReportsList(String login) {
        var user = userRepository.findByLogin(login).orElseThrow();
        List<NutrientsPreconditions> nutrientsPreconditionsList = new ArrayList<>(user.getNutrientsPreconditions());
        Collections.sort(nutrientsPreconditionsList);
        Collections.reverse(nutrientsPreconditionsList);
        return nutrientsPreconditionsList;
    }

    @Override
    public Iterable<NutrientsPreconditions> getNutrientsPreconditionsListByLogin(String login) {
        return getDailyNutritionReportsList(login);
    }

    @Override
    public NutrientsPreconditions addBodyDimensions(String login) {
        return null;
    }
}
