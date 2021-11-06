package com.example.demo.controller;

import com.example.demo.model.MonitoredHealthParametersInfo.MonitoredHealthParameters;
import com.example.demo.repository.MonitoredHealthParametersRepository;
import com.example.demo.service.MonitoredHealthParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MonitoredHealthParametersController {

    @Autowired
    MonitoredHealthParametersService monitoredHealthParametersService;

    @Autowired
    MonitoredHealthParametersRepository monitoredHealthParametersRepository;

    @GetMapping("/monitoredHealthParameters")
    public Iterable<MonitoredHealthParameters> getInfo() {
        return monitoredHealthParametersRepository.findAll();
    }

    @PostMapping("/monitoredHealthParameters/{login}")
    public ResponseEntity<MonitoredHealthParameters> addMonitoredHealthParameters(@RequestBody MonitoredHealthParameters monitoredHealthParameters, @PathVariable String login) {
        monitoredHealthParametersService.addMonitoredHealthParameters(login, monitoredHealthParameters.getSystolicPressure(), monitoredHealthParameters.getDiaSystolicPressure(), monitoredHealthParameters.getBloodSugarLevel());
        return new ResponseEntity<MonitoredHealthParameters>(HttpStatus.OK);

    }
}
