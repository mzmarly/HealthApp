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
    NutrientsCheckerRepository nutrientsCheckerRepository;

    @Override
    public NutrientsChecker setStateFotNutrientsChecker(String login) {
        var user = userRepository.findByLogin(login).orElseThrow();
        NutrientsChecker nutrientsChecker = setStateForNutrientsChecker(user);
        System.out.println(nutrientsChecker.toString());
        nutrientsCheckerRepository.save(nutrientsChecker);
        Set<NutrientsChecker> nutrientsCheckerSet = user.getNutrientsCheckers();
        nutrientsCheckerSet.add(nutrientsChecker);
        user.setNutrientsCheckers(nutrientsCheckerSet);
        user.getNutrientsCheckers().add(nutrientsChecker);
        userRepository.save(user);
        return nutrientsChecker;
    }

    @Override
    public Iterable<NutrientsChecker> getAllNutrientsChecker() {
        return nutrientsCheckerRepository.findAll();
    }

    NutrientsChecker setStateForNutrientsChecker(User user) {
        long size = nutrientsCheckerRepository.findAll().size();
        DailyNutritionReport dailyNutritionReport = getDailyNutritionReports(user.getLogin());

        return new NutrientsChecker(size,
                setStateForCalories(dailyNutritionReport, user),
                setStateForProteins(dailyNutritionReport, user),
                setStateForFats(dailyNutritionReport, user),
                setStateForCholesterol(dailyNutritionReport, user),
                setStateForCarbohydrates(dailyNutritionReport, user)
        );
    }

    NutrientsState setStateForCalories(DailyNutritionReport dailyNutritionReport, User user) {
        if (dailyNutritionReport.getCalories() < caloriesChecker(user) * 0.9) {
            return NutrientsState.TOO_LOW;
        } else if (dailyNutritionReport.getCalories() < caloriesChecker(user) * 1.2) {
            return NutrientsState.CORRECT;
        } else return NutrientsState.TOO_HIGH;
    }

    NutrientsState setStateForProteins(DailyNutritionReport dailyNutritionReport, User user) {
        if (dailyNutritionReport.getDailyProtein_g() < proteinChecker(user) * 0.8) {
            return NutrientsState.TOO_LOW;
        } else if (dailyNutritionReport.getDailyProtein_g() < proteinChecker(user) * 1.2) {
            return NutrientsState.CORRECT;
        } else
            return NutrientsState.TOO_HIGH;
    }

    NutrientsState setStateForFats(DailyNutritionReport dailyNutritionReport, User user) {
        if (dailyNutritionReport.getDailyFat_total_g() < fatChecker(user) * 0.8) {
            return NutrientsState.TOO_LOW;
        } else if (dailyNutritionReport.getDailyProtein_g() < fatChecker(user) * 1.2) {
            return NutrientsState.CORRECT;
        } else
            return NutrientsState.TOO_HIGH;
    }

    NutrientsState setStateForCholesterol(DailyNutritionReport dailyNutritionReport, User user) {
        if (dailyNutritionReport.getDailyFat_total_g() < cholesterolChecker(user) * 0.8) {
            return NutrientsState.TOO_LOW;
        } else if (dailyNutritionReport.getDailyProtein_g() < cholesterolChecker(user) * 1.2) {
            return NutrientsState.CORRECT;
        } else
            return NutrientsState.TOO_HIGH;
    }

    NutrientsState setStateForCarbohydrates(DailyNutritionReport dailyNutritionReport, User user) {
        if (dailyNutritionReport.getDailyFat_total_g() < carbohydratesChecker(user) * 0.8) {
            return NutrientsState.TOO_LOW;
        } else if (dailyNutritionReport.getDailyProtein_g() < carbohydratesChecker(user) * 1.2) {
            return NutrientsState.CORRECT;
        } else
            return NutrientsState.TOO_HIGH;
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