package com.example.demo.serviceImpl;

import com.example.demo.model.UserReport.DiabetesLevel;
import com.example.demo.model.UserReport.UserReport;
import com.example.demo.model.UserReport.HypertensionLevel;
import com.example.demo.repository.UserReportRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserReportServiceImpl implements UserReportService {

    @Autowired
    UserReportRepository userReportRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserReport addUserReport(String login, HypertensionLevel hypertensionLevel, DiabetesLevel diabetesLevel) {
        var user = userRepository.findByLogin(login).orElseThrow();
        long userReportId=userReportRepository.findAll().size();
        UserReport userReport= new UserReport(userReportId,hypertensionLevel,diabetesLevel);
        userReportRepository.save(userReport);
        Set<UserReport> userReportSet = user.getUserReports();
        userReportSet.add(userReport);
        user.setUserReports(userReportSet);
        user.getUserReports().add(userReport);
        userRepository.save(user);
        return userReport;
    }
}
