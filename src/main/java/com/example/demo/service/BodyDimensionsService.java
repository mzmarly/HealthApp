package com.example.demo.service;

import com.example.demo.model.BodyDimensionsInfo.BodyDimensions;


public interface BodyDimensionsService {
    BodyDimensions create(BodyDimensions bodyDimensions);

    BodyDimensions addBodyDimensions(String login, double shoulders, double waist, double hips);

    void removeBodyDimensions(Long id);
}
