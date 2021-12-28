package com.example.demo.service;

import com.example.demo.model.BodyDimensionsInfo.BodyDimensions;

public interface BodyDimensionsService {
    BodyDimensions create(BodyDimensions bodyDimensions);

    BodyDimensions addBodyDimensions(String login, double shoulders, double waist, double hips, double weight);

    void removeBodyDimensions(Long id);

    double[] getMinMaxParams(String login);

    double calculateWHR(String login);

    double calculateBMI(String login);

    double getAverageByMonth(String login, String month);

    Iterable<BodyDimensions> getAllBodyDimensions();

    Iterable<BodyDimensions> getBodyDimensionsByLogin(String login);

    Iterable<BodyDimensions> getBodyDimensionsByLoginAndDate(String login, int day, int month, int year);

    Iterable<BodyDimensions> getBodyDimensionsByLoginAndMonth(String login, int month);
}
