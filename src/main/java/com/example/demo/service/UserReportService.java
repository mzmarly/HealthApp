package com.example.demo.service;

import com.example.demo.model.UserReport;
import com.example.demo.model.HypertensionLevel;

public interface UserReportService {

    UserReport addUserReport(String login, HypertensionLevel hypertensionLevel);

}
