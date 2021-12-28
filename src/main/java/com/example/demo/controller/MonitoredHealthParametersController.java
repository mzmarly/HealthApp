package com.example.demo.controller;

import com.example.demo.model.BodyDimensionsInfo.BodyDimensions;
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
    public Iterable<MonitoredHealthParameters> getIAllMonitoredHealthParameters() {
        return monitoredHealthParametersService.getIAllMonitoredHealthParameters();
    }

    @GetMapping("/monitoredHealthParameters/{login}")
    public Iterable<MonitoredHealthParameters> getMonitoredHealthParametersForUser( @PathVariable String login) {
        return monitoredHealthParametersService.getMonitoredHealthParametersByLogin(login);
    }
    @GetMapping("/monitoredHealthParameters/{login}/{day}/{month}/{year}")
    public Iterable<MonitoredHealthParameters> getMonitoredHealthParametersForUser(@PathVariable String login, @PathVariable int day, @PathVariable int month, @PathVariable int year) {
        return monitoredHealthParametersService.getMonitoredHealthParametersByLoginAndDate(login,day,month,year);
    }
    @GetMapping("/monitoredHealthParameters/{login}/{month}")
    public Iterable<MonitoredHealthParameters> getMonitoredHealthParametersForUserByMonth(@PathVariable String login,@PathVariable int month) {
        return monitoredHealthParametersService.getMonitoredHealthParametersByLoginAndMonth(login,month);
    }
    @PostMapping("/monitoredHealthParameters/{login}")
    public ResponseEntity<MonitoredHealthParameters> addMonitoredHealthParameters(@RequestBody MonitoredHealthParameters monitoredHealthParameters, @PathVariable String login) {
        monitoredHealthParametersService.addMonitoredHealthParameters(login, monitoredHealthParameters.getSystolicPressure(), monitoredHealthParameters.getDiaSystolicPressure(), monitoredHealthParameters.getBloodSugarLevel());
        return new ResponseEntity<MonitoredHealthParameters>(HttpStatus.OK);
    }

    @DeleteMapping("/monitoredHealthParameters/{id}")
    public ResponseEntity<HttpStatus> removeMonitoredHealthParameters(@PathVariable(value = "id") long id) {
        monitoredHealthParametersService.removeMonitoredHealthParameter(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }
}