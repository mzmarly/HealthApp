package com.example.demo.controller;

import com.example.demo.model.MonitoredHealthParametersInfo.MonitoredHealthParameters;
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

    @GetMapping("/monitoredHealthParameters")
    public Iterable<MonitoredHealthParameters> getInfo() {
        return monitoredHealthParametersService.getIAllMonitoredHealthParameters();
    }

    @PostMapping("/monitoredHealthParameters/{login}")
    public ResponseEntity<MonitoredHealthParameters> addMonitoredHealthParameters(@RequestBody MonitoredHealthParameters monitoredHealthParameters, @PathVariable String login) {
        monitoredHealthParametersService.addMonitoredHealthParameters(login, monitoredHealthParameters.getSystolicPressure(), monitoredHealthParameters.getDiaSystolicPressure(), monitoredHealthParameters.getBloodSugarLevel());
        return new ResponseEntity<MonitoredHealthParameters>(HttpStatus.OK);

    }

    @DeleteMapping("/removeBasicUserData/{id}")
    public ResponseEntity<HttpStatus> removeRating(@PathVariable(value = "id") long id) {
        monitoredHealthParametersService.removeMonitoredHealthParameter(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }
}//MonitoredHealthParameters