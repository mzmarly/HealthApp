package com.example.demo.service.diseasesChecker;

public interface DiseasesCheckerService {
    void checkHypertension(String login);
    void chekDiabetes(String login);
    void checkBMI(String login);
    void checkWHR(String login);
}
