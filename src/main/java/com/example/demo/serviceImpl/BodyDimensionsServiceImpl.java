package com.example.demo.serviceImpl;

import com.example.demo.model.BasicUserDataInfo.BasicUserData;
import com.example.demo.model.BodyDimensionsInfo.BodyDimensions;
import com.example.demo.repository.BodyDimensionsRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BodyDimensionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class BodyDimensionsServiceImpl implements BodyDimensionsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BodyDimensionsRepository bodyDimensionsRepository;

    @Override
    public BodyDimensions create(BodyDimensions bodyDimensions) {
        return bodyDimensionsRepository.save(bodyDimensions);
    }

    @Override
    public BodyDimensions addBodyDimensions(String login, double shoulders, double waist, double hips) {
        LocalDate date = LocalDate.now();
        long bodyDimensionsId = bodyDimensionsRepository.findAll().size() + 1;
        var user = userRepository.findByLogin(login).orElseThrow();
        BodyDimensions bodyDimensions = new BodyDimensions(bodyDimensionsId, date, shoulders, waist, hips);
        bodyDimensionsRepository.save(bodyDimensions);
        Set<BodyDimensions> bodyDimensionsSet = user.getBodyDimensionsons();
        bodyDimensionsSet.add(bodyDimensions);
        user.setBodyDimensionsons(bodyDimensionsSet);
        user.getBodyDimensionsons().add(bodyDimensions);
        userRepository.save(user);
        return bodyDimensions;
    }

    @Override
    public void removeBodyDimensions(Long id) {
        bodyDimensionsRepository.deleteById(id);

    }
}
