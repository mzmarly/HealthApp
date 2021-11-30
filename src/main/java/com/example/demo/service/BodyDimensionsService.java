package com.example.demo.service;

import com.example.demo.model.BodyDimensionsInfo.BodyDimensions;


public interface BodyDimensionsService {
    BodyDimensions create(BodyDimensions bodyDimensions);

    BodyDimensions addBodyDimensions(String login, double shoulders, double waist, double hips,double weight);

    void removeBodyDimensions(Long id);

    double[] getMinMaxParams(String login);

    double calculateWHR(String login);

    double calculateBMI(String login);

    double getAverageByMonth(String login, String month);
}
