package com.example.demo.serviceImpl;

import com.example.demo.model.BasicUserDataInfo.BasicUserData;
import com.example.demo.model.BodyDimensionsInfo.BodyDimensions;
import com.example.demo.model.DailyStars;
import com.example.demo.model.User;
import com.example.demo.model.UserReport.DailyNutritionReport;
import com.example.demo.model.UserReport.UserReport;
import com.example.demo.repository.UserReportRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.DailyStarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class DailyStarsServiceImpl implements DailyStarsService {

    @Autowired
    UserReportRepository userReportRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void calculateDailyLoginInRov(String login) {
        LocalDate date = LocalDate.now();
        var user = userRepository.findByLogin(login).orElseThrow();
        UserReport userReport = getUserReport(login);
        DailyStars dailyStars = getLastDailyStars(login);
        if (userReport.getDate().getDayOfMonth() == date.getDayOfMonth()) {
            dailyStars.setLoginNumberDayInRow(dailyStars.getLoginNumberDayInRow() + 1);

        } else {
            dailyStars.setLoginNumberDayInRow(0);
            dailyStars.setGoodReportInRow(0);
        }
        userRepository.save(user);
    }

    @Override
    public void calculateDailyReportInRov(String login) {

    }

//    boolean lastReportChecker(UserReport userReport){
//        userReport.gee
//    }


    public UserReport getLastUserReport(String login) {
        var user = userRepository.findByLogin(login).orElseThrow();
        Set<UserReport> set = user.getUserReports();
        UserReport[] userReportsArray = set.toArray(set.toArray(new UserReport[0]));
        return userReportsArray[0];
    }

    public DailyStars getLastDailyStars(String login) {
        var user = userRepository.findByLogin(login).orElseThrow();
        Set<DailyStars> set = user.getDailyStars();
        if (set.isEmpty()) {
            DailyStars dailyStars = new DailyStars();
            Set<DailyStars> dailyStarsSet = user.getDailyStars();
            dailyStarsSet.add(dailyStars);
            user.setDailyStars(dailyStarsSet);
            user.getDailyStars().add(dailyStars);
            userRepository.save(user);
            return dailyStars;
        } else {
            DailyStars[] dailyStarsArray = set.toArray(set.toArray(new DailyStars[0]));
            return dailyStarsArray[0];
        }
    }

//    public DailyNutritionReport getDailyNutritionReports(String login) {
//        var userReport = getUserReport(login);
//        List<DailyNutritionReport> dailyNutritionReportsList = new ArrayList<>(userReport.getDailyNutritionReports());
//        Collections.sort(dailyNutritionReportsList);
//        Collections.reverse(dailyNutritionReportsList);
//        System.out.println(dailyNutritionReportsList.get(0).getDailyNutritionReportId());
//        return dailyNutritionReportsList.get(0);
//    }

    public UserReport getUserReport(String login) {
        var user = userRepository.findByLogin(login).orElseThrow();
        List<UserReport> userReportList = new ArrayList<>(user.getUserReports());
        Collections.sort(userReportList);
        Collections.reverse(userReportList);
        return userReportList.get(0);
    }
}
