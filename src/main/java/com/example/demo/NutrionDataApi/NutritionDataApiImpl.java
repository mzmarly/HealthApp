package com.example.demo.NutrionDataApi;

import com.example.demo.model.MonitoredHealthParametersInfo.MonitoredHealthParameters;
import com.example.demo.model.NutrientsChecker.NutrientsPreconditions;
import com.example.demo.model.Nutrtion.Nutrition;
import com.example.demo.model.Nutrtion.NutritionJSON;
import com.example.demo.model.UserReport.DailyNutritionReport;
import com.example.demo.model.UserReport.UserReport;
import com.example.demo.repository.DailyNutritionReportRepository;
import com.example.demo.repository.NutritionDataRepository;
import com.example.demo.repository.UserReportRepository;
import com.example.demo.repository.UserRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class NutritionDataApiImpl implements NutritionDataApi {

    @Autowired
    NutritionDataRepository nutritionDataRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserReportRepository userReportRepository;

    @Autowired
    DailyNutritionReportRepository dailyNutritionReportRepository;

    @Override
    public String addNutritionByWeight(String login, String food, double portionWeight) {
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
        JsonToObject(login, response.getBody(), portionWeight);
        log.info(response.getBody());
        return response.getBody();
    }

    @Override
    public void removeFoodById(Long id) {
        nutritionDataRepository.deleteById(id);
    }

    @Override
    public void generateDailyReport(String login) {
        var user = userRepository.findByLogin(login).orElseThrow();

    }

    @Override
    public DailyNutritionReport addSumUpNutrition(String login) {
        var userReport = getLastUserReport(login);
        long nutritionReportSize = dailyNutritionReportRepository.findAll().size();
        LocalDate date = LocalDate.now();
        DailyNutritionReport dailyNutritionReport = new DailyNutritionReport();
        List<Nutrition> nutritionListByDay = getNutritionListForUser(login);
        if (!nutritionListByDay.isEmpty()) {
            for (Nutrition i : nutritionListByDay) {
                dailyNutritionReport.setCalories(i.getCalories() + dailyNutritionReport.getCalories());
                dailyNutritionReport.setDailyCholesterol_mg(i.getCholesterol_mg() + dailyNutritionReport.getDailyCholesterol_mg());
                dailyNutritionReport.setDailyCarbohydrates_total_g(i.getCarbohydrates_total_g() + dailyNutritionReport.getDailyCarbohydrates_total_g());
                dailyNutritionReport.setDailyFiber_g(i.getFiber_g() + dailyNutritionReport.getDailyFiber_g());
                dailyNutritionReport.setDailyFat_saturated_g(i.getFat_saturated_g() + dailyNutritionReport.getDailyFat_saturated_g());
                dailyNutritionReport.setDailyFat_total_g(i.getFat_total_g() + dailyNutritionReport.getDailyFat_total_g());
                dailyNutritionReport.setDailyPotassium_mg(i.getPotassium_mg() + dailyNutritionReport.getDailyPotassium_mg());
                dailyNutritionReport.setDailyProtein_g(i.getProtein_g() + dailyNutritionReport.getDailyProtein_g());
                dailyNutritionReport.setDailySodium_mg(i.getSodium_mg() + dailyNutritionReport.getDailySodium_mg());
                dailyNutritionReport.setDailySugar_g(i.getSugar_g() + dailyNutritionReport.getDailySugar_g());
                dailyNutritionReport.setDailyServing_size_g(i.getServing_size_g() + dailyNutritionReport.getDailyServing_size_g());
            }
            dailyNutritionReport.setDailyNutritionReportId(nutritionReportSize);
            dailyNutritionReport.setDate(date);
        }
        dailyNutritionReportRepository.save(dailyNutritionReport);
        Set<DailyNutritionReport> dailyNutritionReportSet = userReport.getDailyNutritionReports();
        dailyNutritionReportSet.add(dailyNutritionReport);
        userReport.setDailyNutritionReports(dailyNutritionReportSet);
        userReport.getDailyNutritionReports().add(dailyNutritionReport);
        userReportRepository.save(userReport);
        return dailyNutritionReport;
    }

    @Override
    public DailyNutritionReport getDailySumUpByLogin(String login) {
        return getDailyNutritionReports(login);
    }

    @Override
    public Iterable<DailyNutritionReport> getDailySumUpListByLogin(String login) {
//        var userReport = getLastUserReport(login);
//        return userReport.getDailyNutritionReports();
        return getDailyNutritionReportsList(login);
    }

    @Override
    public Iterable<Nutrition> geNutritionByLogin(String login) {
        var user = userRepository.findByLogin(login).orElseThrow();
        List<Nutrition> nutritionList = new ArrayList<>();
        for (Nutrition i : user.getNutritions()) {
            nutritionList.add(i);
        }
        Collections.sort(nutritionList);

        return nutritionList;
    }


    public void JsonToObject(String login, String JSONBody, double portionWeight) {
        long nutritionId = nutritionDataRepository.findAll().size();
        var user = userRepository.findByLogin(login).orElseThrow();
        double factor = portionWeight / 100;
        LocalDate date = LocalDate.now();
        String response = JSONBody;
        Gson gson = new Gson();
        NutritionJSON[] nutritionDTO = gson.fromJson(response, NutritionJSON[].class);
        System.out.println(nutritionDTO[0].getName());
        Nutrition nutrition = new Nutrition(nutritionId,
                date,
                nutritionDTO[0].getName(),
                nutritionDTO[0].getCalories() * factor,
                nutritionDTO[0].getServing_size_g() * factor,
                nutritionDTO[0].getFat_total_g() * factor,
                nutritionDTO[0].getFat_saturated_g() * factor,
                nutritionDTO[0].getProtein_g() * factor,
                nutritionDTO[0].getSodium_mg() * factor,
                nutritionDTO[0].getPotassium_mg() * factor,
                nutritionDTO[0].getCholesterol_mg() * factor,
                nutritionDTO[0].getCarbohydrates_total_g() * factor,
                nutritionDTO[0].getFiber_g() * factor,
                nutritionDTO[0].getSugar_g() * factor
        );
        nutritionDataRepository.save(nutrition);
        Set<Nutrition> nutritionSet = user.getNutritions();
        nutritionSet.add(nutrition);
        user.setNutritions(nutritionSet);
        user.getNutritions().add(nutrition);
        userRepository.save(user);
    }

    public List<Nutrition> getNutritionListForUser(String login) {
        var user = userRepository.findByLogin(login).orElseThrow();
        boolean exist = false;
        LocalDate date = LocalDate.now();
        List<Nutrition> nutritionList = new ArrayList<>();
        for (Nutrition i : user.getNutritions()) {
            if (i.getDate().getDayOfMonth() == date.getDayOfMonth() && i.getDate().getMonthValue() == date.getMonthValue()) {
                nutritionList.add(i);
                exist = true;
            }
        }
        return nutritionList;
    }

    public DailyNutritionReport getDailyNutritionReports(String login) {
        var userReport = getLastUserReport(login);
        List<DailyNutritionReport> dailyNutritionReportsList = new ArrayList<>(userReport.getDailyNutritionReports());
        Collections.sort(dailyNutritionReportsList);
        Collections.reverse(dailyNutritionReportsList);
        System.out.println(dailyNutritionReportsList.get(0).getDailyNutritionReportId());
        return dailyNutritionReportsList.get(0);
    }

    public List<DailyNutritionReport> getDailyNutritionReportsList(String login) {
        var userReport = getLastUserReport(login);
        List<DailyNutritionReport> dailyNutritionReportsList = new ArrayList<>(userReport.getDailyNutritionReports());
        Collections.sort(dailyNutritionReportsList);
        Collections.reverse(dailyNutritionReportsList);
        System.out.println(dailyNutritionReportsList.get(0).getDailyNutritionReportId());
        return dailyNutritionReportsList;
    }

    public UserReport getLastUserReport(String login) {
        var user = userRepository.findByLogin(login).orElseThrow();
        Set<UserReport> set = user.getUserReports();
        if (set.isEmpty()) {
            return new UserReport();
        } else {
            UserReport[] userArray = set.toArray(set.toArray(new UserReport[0]));
            return userArray[0];
        }
    }
}