package com.example.demo.serviceImpl.diseasesCheckerImpl;

import com.example.demo.model.UserReport;
import com.example.demo.model.HypertensionLevel;
import com.example.demo.model.MonitoredHealthParametersInfo.MonitoredHealthParameters;
import com.example.demo.repository.MonitoredHealthParametersRepository;
import com.example.demo.repository.UserReportRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.diseasesChecker.HypertensionCheckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class HypertensionCheckerServiceImpl implements HypertensionCheckerService {

    @Autowired
    MonitoredHealthParametersRepository monitoredHealthParametersRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserReportRepository userReportRepository;

    @Override
    public void checkHypertension(String login) {
        var user = userRepository.findByLogin(login).orElseThrow();
        UserReport userReport =getLastUserReport(login);
        List<MonitoredHealthParameters> monitoredHealthParametersList = getListMonitoredHealthParametersForUser(login);
        MonitoredHealthParameters recentHealthParameters=monitoredHealthParametersList.get(monitoredHealthParametersList.size()-1);
        int systolicPressure=recentHealthParameters.getSystolicPressure();
        int diaSystolicPressure=recentHealthParameters.getDiaSystolicPressure();
        if(systolicPressure<=120 && diaSystolicPressure<=80){
            userReport.setHypertension(HypertensionLevel.OPTIMAL);
        }
        else if(systolicPressure<=129 && diaSystolicPressure<=84){
            userReport.setHypertension(HypertensionLevel.NORMAL);
        }
        else if(systolicPressure<=139 && diaSystolicPressure<=89){
            userReport.setHypertension(HypertensionLevel.HIGH_NORMAL);
        }
        else if(systolicPressure<=159 && diaSystolicPressure<=99){
            userReport.setHypertension(HypertensionLevel.GRADE_1_HYPERTENSION);
        }
        else if(systolicPressure<=179 && diaSystolicPressure<=109){
            userReport.setHypertension(HypertensionLevel.GRADE_2_HYPERTENSION);
        }
        else if(systolicPressure>=180 && diaSystolicPressure>=110){
            userReport.setHypertension(HypertensionLevel.GRADE_3_HYPERTENSION);
        }
        else if(systolicPressure>=140 && diaSystolicPressure<=90){
            userReport.setHypertension(HypertensionLevel.ISOLATED_SYSTOLIC_HYPERTENSION);
        }
        userRepository.save(user);
    }

    public List<MonitoredHealthParameters> getListMonitoredHealthParametersForUser(String login) {
        var user = userRepository.findByLogin(login).orElseThrow();
        System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHEEEEEEEEEEEEEEEEEEEEEEEEEEJ");
        List<MonitoredHealthParameters> monitoredHealthParametersList = new ArrayList<>();
        for (MonitoredHealthParameters i : user.getMonitoredHealthParameters()) {
            System.out.println("ID" + i.getMonitoredHealthParametersId());
            monitoredHealthParametersList.add(i);
        }
        System.out.println(monitoredHealthParametersList.size());
        Collections.sort(monitoredHealthParametersList);

        return monitoredHealthParametersList;
    }

    public UserReport getLastUserReport(String login){
        var user = userRepository.findByLogin(login).orElseThrow();
        Set<UserReport> set=user.getUserReports();
        UserReport[] userArray= set.toArray(set.toArray(new UserReport[0]));
        return userArray[0];
    }
}
