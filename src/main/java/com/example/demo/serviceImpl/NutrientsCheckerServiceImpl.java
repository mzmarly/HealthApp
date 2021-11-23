package com.example.demo.serviceImpl;

import com.example.demo.model.BasicUserDataInfo.BasicUserData;
import com.example.demo.model.BasicUserDataInfo.PhysicalActivity;
import com.example.demo.model.BasicUserDataInfo.Sex;
import com.example.demo.model.BodyDimensionsInfo.BodyDimensions;
import com.example.demo.model.NutrientsChecker.NutrientsChecker;
import com.example.demo.model.NutrientsChecker.NutrientsState;
import com.example.demo.model.User;
import com.example.demo.model.UserReport.DailyNutritionReport;
import com.example.demo.model.UserReport.UserReport;
import com.example.demo.repository.BodyDimensionsRepository;
import com.example.demo.repository.DailyNutritionReportRepository;
import com.example.demo.repository.NutrientsCheckerRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.NutrientsCheckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class NutrientsCheckerServiceImpl implements NutrientsCheckerService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BodyDimensionsRepository bodyDimensionsRepository;

    @Autowired
    NutrientsCheckerRepository nutrientsCheckerRepository;

    @Autowired
    DailyNutritionReportRepository dailyNutritionReportRepository;

    @Override
    public void setStateFotNutrientsChecker(String login) {
        var user = userRepository.findByLogin(login).orElseThrow();
        NutrientsChecker nutrientsChecker = new NutrientsChecker();
        DailyNutritionReport dailyNutritionReport = getDailyNutritionReports(login);
        setStateForCalories(nutrientsChecker, dailyNutritionReport, user);
        setStateForProteins(nutrientsChecker, dailyNutritionReport, user);
        setStateForFats(nutrientsChecker, dailyNutritionReport, user);
        setStateForCholesterol(nutrientsChecker, dailyNutritionReport, user);


    }

    void setStateForCalories(NutrientsChecker nutrientsChecker, DailyNutritionReport dailyNutritionReport, User user) {
        if (dailyNutritionReport.getCalories() < caloriesChecker(user) * 0.9) {
            nutrientsChecker.setCaloriesState(NutrientsState.TOO_LOW);
        } else if (dailyNutritionReport.getCalories() < caloriesChecker(user) * 1.2) {
            nutrientsChecker.setCaloriesState(NutrientsState.CORRECT);
        } else nutrientsChecker.setCaloriesState(NutrientsState.TOO_HIGH);
    }

    void setStateForProteins(NutrientsChecker nutrientsChecker, DailyNutritionReport dailyNutritionReport, User user) {
        if (dailyNutritionReport.getDailyProtein_g() < proteinChecker(user) * 0.8) {
            nutrientsChecker.setCaloriesState(NutrientsState.TOO_LOW);
        } else if (dailyNutritionReport.getDailyProtein_g() < proteinChecker(user) * 1.2) {
            nutrientsChecker.setCaloriesState(NutrientsState.CORRECT);
        } else
            nutrientsChecker.setCaloriesState(NutrientsState.TOO_HIGH);
    }
    void setStateForFats(NutrientsChecker nutrientsChecker, DailyNutritionReport dailyNutritionReport, User user) {
        if (dailyNutritionReport.getDailyFat_total_g() < fatChecker(user) * 0.8) {
            nutrientsChecker.setFatsState(NutrientsState.TOO_LOW);
        } else if (dailyNutritionReport.getDailyProtein_g() < fatChecker(user) * 1.2) {
            nutrientsChecker.setFatsState(NutrientsState.CORRECT);
        } else
            nutrientsChecker.setFatsState(NutrientsState.TOO_HIGH);
    }
    void setStateForCholesterol(NutrientsChecker nutrientsChecker, DailyNutritionReport dailyNutritionReport, User user) {
        if (dailyNutritionReport.getDailyFat_total_g() < cholesterolChecker(user) * 0.8) {
            nutrientsChecker.setFatsState(NutrientsState.TOO_LOW);
        } else if (dailyNutritionReport.getDailyProtein_g() < cholesterolChecker(user) * 1.2) {
            nutrientsChecker.setFatsState(NutrientsState.CORRECT);
        } else
            nutrientsChecker.setFatsState(NutrientsState.TOO_HIGH);
    }
    void setStateForCarbohydratesC(NutrientsChecker nutrientsChecker, DailyNutritionReport dailyNutritionReport, User user) {
        if (dailyNutritionReport.getDailyFat_total_g() < carbohydratesChecker(user) * 0.8) {
            nutrientsChecker.setFatsState(NutrientsState.TOO_LOW);
        } else if (dailyNutritionReport.getDailyProtein_g() < carbohydratesChecker(user) * 1.2) {
            nutrientsChecker.setFatsState(NutrientsState.CORRECT);
        } else
            nutrientsChecker.setFatsState(NutrientsState.TOO_HIGH);
    }


    double caloriesChecker(User user) {
        BasicUserData basicUserData = getBasicUserData(user);
        Sex sex = basicUserData.getSex();
        BodyDimensions bodyDimensions = getLastBodyDimension(user);
        PhysicalActivity physicalActivity = basicUserData.getPhysicalActivity();
        switch (sex) {
            case FEMALE:
                return femaleCaloriesChecker(basicUserData, bodyDimensions) * physicalActivity.getActivityRatio();
            case MALE:
                return maleCaloriesChecker(basicUserData, bodyDimensions) * physicalActivity.getActivityRatio();
            default:
                return 0;
        }
    }

    double proteinChecker(User user) {
        BasicUserData basicUserData = getBasicUserData(user);
        PhysicalActivity physicalActivity = basicUserData.getPhysicalActivity();
        switch (physicalActivity) {
            case LESS_ACTIVE:
            case FAIRLY_ACTIVE:
                return getLastBodyDimension(user).getWeight() * 0.9;
            case ACTIVE_ACROSS_THE_WEEK:
                return getLastBodyDimension(user).getWeight() * 1.4;
            case ACTIVE_EVERY_DAY:
                return getLastBodyDimension(user).getWeight() * 2;
            default:
                return 0;
        }
    }

    double fatChecker(User user) {
        return caloriesChecker(user) / 25; //zwraca wynik w gramach (kalorie/9/0.2)
    }

    double cholesterolChecker(User user) {
        return 200; //ogólnie zakres to 125-200
    }

    double carbohydratesChecker(User user) {
        return caloriesChecker(user) * 2; //
    }

    double femaleCaloriesChecker(BasicUserData basicUserData, BodyDimensions bodyDimensions) {
        return 655.1 + 9.567 * bodyDimensions.getWeight() + 1.85 * basicUserData.getHeight() - 4.68 * basicUserData.getAge();
    }

    double maleCaloriesChecker(BasicUserData basicUserData, BodyDimensions bodyDimensions) {
        return 66.47 + 13.7 * bodyDimensions.getWeight() + 5 * basicUserData.getHeight() - 6.76 * basicUserData.getAge();
    }

    public BasicUserData getBasicUserData(User user) {
        Set<BasicUserData> basicUserDataSet = user.getBasicUserData();
        BasicUserData[] basicUserDataArray = basicUserDataSet.toArray(new BasicUserData[0]);
        return basicUserDataArray[0];
    }

    public BodyDimensions getLastBodyDimension(User user) {
        List<BodyDimensions> bodyDimensionsList = new ArrayList<>();
        for (BodyDimensions i : user.getBodyDimensionsons()) {
            System.out.println("ID" + i.getBodyDimensionsId());
            bodyDimensionsList.add(i);
        }
        Collections.sort(bodyDimensionsList);

        return bodyDimensionsList.get(bodyDimensionsList.size() - 1);
    }

    public UserReport getLastUserReport(String login) {
        var user = userRepository.findByLogin(login).orElseThrow();
        Set<UserReport> set = user.getUserReports();
        UserReport[] userReportsArray = set.toArray(set.toArray(new UserReport[0]));
        return userReportsArray[0];
    }

    public DailyNutritionReport getDailyNutritionReports(String login) {
        var userReport = getLastUserReport(login);
        List<DailyNutritionReport> dailyNutritionReportsList = new ArrayList<>(userReport.getDailyNutritionReports());
        Collections.sort(dailyNutritionReportsList);
        Collections.reverse(dailyNutritionReportsList);
        System.out.println(dailyNutritionReportsList.get(0).getDailyNutritionReportId());
        return dailyNutritionReportsList.get(0);
    }
}
