package com.example.demo.serviceImpl;

import com.example.demo.model.MonitoredHealthParametersInfo.MonitoredHealthParameters;
import com.example.demo.repository.MonitoredHealthParametersRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.MonitoredHealthParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public MonitoredHealthParameters addMonitoredHealthParameters(String login, double weight, int systolicPressure, int diaSystolicPressure, int bloodSugarLevel) {
        LocalDate date = LocalDate.now();
        long basicMonitoredHealthParametersId = monitoredHealthParametersRepository.findAll().size();
        var user = userRepository.findByLogin(login).orElseThrow();
        MonitoredHealthParameters monitoredHealthParameters = new MonitoredHealthParameters(basicMonitoredHealthParametersId,date, weight, systolicPressure, diaSystolicPressure,bloodSugarLevel);
        monitoredHealthParametersRepository.save(monitoredHealthParameters);
        Set<MonitoredHealthParameters> monitoredHealthParametersSet=user.getMonitoredHealthParameters();
        monitoredHealthParametersSet.add(monitoredHealthParameters);
        user.setMonitoredHealthParameters(monitoredHealthParametersSet);
        user.getMonitoredHealthParameters().add(monitoredHealthParameters);
        userRepository.save(user);
        return monitoredHealthParameters;
    }

    @Override
    public void MonitoredHealthParameters(Long id) {monitoredHealthParametersRepository.deleteById(id);

    }
}
