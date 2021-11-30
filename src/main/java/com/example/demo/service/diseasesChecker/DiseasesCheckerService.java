package com.example.demo.service.diseasesChecker;

public interface DiseasesCheckerService {
    void checkHypertension(String login);
    void chekDiabetes(String login);
    double checkBMI(String login);
    double checkWHR(String login);
}
