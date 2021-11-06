package com.example.demo.service;

import com.example.demo.model.MonitoredHealthParametersInfo.MonitoredHealthParameters;

public interface MonitoredHealthParametersService {

    MonitoredHealthParameters create(MonitoredHealthParameters monitoredHealthParameters);

    MonitoredHealthParameters addMonitoredHealthParameters(String login,  int systolicPressure, int diaSystolicPressure, int bloodSugarLevel);

    void MonitoredHealthParameters(Long id);
}
