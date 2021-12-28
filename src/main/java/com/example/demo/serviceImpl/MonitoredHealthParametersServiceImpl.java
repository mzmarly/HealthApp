package com.example.demo.serviceImpl;

import com.example.demo.model.BodyDimensionsInfo.BodyDimensions;
import com.example.demo.model.MonitoredHealthParametersInfo.MonitoredHealthParameters;
import com.example.demo.repository.MonitoredHealthParametersRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.MonitoredHealthParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class MonitoredHealthParametersServiceImpl implements MonitoredHealthParametersService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MonitoredHealthParametersRepository monitoredHealthParametersRepository;

    @Override
    public MonitoredHealthParameters create(MonitoredHealthParameters monitoredHealthParameters) {
        return monitoredHealthParametersRepository.save(monitoredHealthParameters);
    }

    @Override
    public MonitoredHealthParameters addMonitoredHealthParameters(String login, int systolicPressure, int diaSystolicPressure, int bloodSugarLevel) {
        LocalDate date = LocalDate.now();
        long basicMonitoredHealthParametersId = monitoredHealthParametersRepository.findAll().size();
        var user = userRepository.findByLogin(login).orElseThrow();
        MonitoredHealthParameters monitoredHealthParameters = new MonitoredHealthParameters(basicMonitoredHealthParametersId, date, systolicPressure, diaSystolicPressure, bloodSugarLevel);
        monitoredHealthParametersRepository.save(monitoredHealthParameters);
        Set<MonitoredHealthParameters> monitoredHealthParametersSet = user.getMonitoredHealthParameters();
        monitoredHealthParametersSet.add(monitoredHealthParameters);
        user.setMonitoredHealthParameters(monitoredHealthParametersSet);
        user.getMonitoredHealthParameters().add(monitoredHealthParameters);
        userRepository.save(user);
        return monitoredHealthParameters;
    }

    @Override
    public void removeMonitoredHealthParameter(Long id) {
        monitoredHealthParametersRepository.deleteById(id);

    }

    @Override
    public Iterable<MonitoredHealthParameters> getIAllMonitoredHealthParameters() {
        return monitoredHealthParametersRepository.findAll();
    }

    @Override
    public Iterable<MonitoredHealthParameters> getMonitoredHealthParametersByLogin(String login) {
        var user = userRepository.findByLogin(login).orElseThrow();
        List<MonitoredHealthParameters> monitoredHealthParametersList = new ArrayList<>();
        for (MonitoredHealthParameters i : user.getMonitoredHealthParameters()) {
            monitoredHealthParametersList.add(i);
        }
        Collections.sort(monitoredHealthParametersList);

        return monitoredHealthParametersList;

    }

    @Override
    public Iterable<MonitoredHealthParameters> getMonitoredHealthParametersByLoginAndDate(String login, int day, int month, int year) {
        List<MonitoredHealthParameters> userList=getMonitoredHealthParametersListForUser(login);
        List<MonitoredHealthParameters> filterList = new ArrayList<>();
        for(MonitoredHealthParameters parameters:userList){
            if(parameters.getDate().getDayOfMonth()==day &&parameters.getDate().getMonthValue()==month &&parameters.getDate().getYear()==year){
                filterList.add(parameters);
            }
        }
        return filterList;
    }

    @Override
    public Iterable<MonitoredHealthParameters> getMonitoredHealthParametersByLoginAndMonth(String login, int month) {
        List<MonitoredHealthParameters> userList=getMonitoredHealthParametersListForUser(login);
        List<MonitoredHealthParameters> filterList = new ArrayList<>();
        for(MonitoredHealthParameters parameters:userList){
            if(parameters.getDate().getMonthValue()==month){
                filterList.add(parameters);
            }
        }
        return filterList;
    }

    public List<MonitoredHealthParameters>getMonitoredHealthParametersListForUser(String login) {
        var user = userRepository.findByLogin(login).orElseThrow();
        List<MonitoredHealthParameters> monitoredHealthParametersList = new ArrayList<>(user.getMonitoredHealthParameters());
        Collections.sort(monitoredHealthParametersList);

        return monitoredHealthParametersList;
    }
}
