package com.example.demo.service;

import com.example.demo.model.UserReport.DiabetesLevel;
import com.example.demo.model.UserReport.UserReport;
import com.example.demo.model.UserReport.HypertensionLevel;

public interface UserReportService {

    UserReport addUserReport(String login, HypertensionLevel hypertensionLevel, DiabetesLevel diabetesLevel);

}
