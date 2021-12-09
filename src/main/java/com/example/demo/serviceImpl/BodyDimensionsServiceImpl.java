package com.example.demo.serviceImpl;

import com.example.demo.model.BodyDimensionsInfo.BodyDimensions;
import com.example.demo.repository.BodyDimensionsRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BodyDimensionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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
    public BodyDimensions addBodyDimensions(String login, double shoulders, double waist, double hips,double weight) {
        LocalDate date = LocalDate.now();
        long bodyDimensionsId = bodyDimensionsRepository.findAll().size();
        var user = userRepository.findByLogin(login).orElseThrow();
        BodyDimensions bodyDimensions = new BodyDimensions(bodyDimensionsId, date, shoulders, waist, hips,weight);
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

    @Override
    public double[] getMinMaxParams(String login) {
        List<BodyDimensions> bodyDimensionsList = getBodyDimensionsListForUser(login);
        double min = bodyDimensionsList.get(0).getHips();
        double max = bodyDimensionsList.get(0).getHips();

        int size = bodyDimensionsList.size();
        if (size >= 7) {
            for (int i = size - 1; i >= size - 7; i--) {
                if (max < bodyDimensionsList.get(i).getHips())
                    max = bodyDimensionsList.get(i).getHips();
                if (min > bodyDimensionsList.get(i).getHips())
                    min = bodyDimensionsList.get(i).getHips();
            }

        } else System.out.println("za malo danych");
        System.out.println("min " + min + " max " + max);
        return new double[]{min, max};
    }

    @Override
    public double calculateWHR(String login) {
        List<BodyDimensions> bodyDimensionsList = getBodyDimensionsListForUser(login);
        int size=bodyDimensionsList.size()-1;
        return bodyDimensionsList.get(size).getWaist()/bodyDimensionsList.get(size).getHips();
    }

    @Override
    public double calculateBMI(String login) {
        List<BodyDimensions> bodyDimensionsList = getBodyDimensionsListForUser(login);
        var user = userRepository.findByLogin(login).orElseThrow();
        int size=bodyDimensionsList.size()-1;

        double height = user.getBasicUserData().stream().findFirst().get().getHeight();

        return bodyDimensionsList.get(size).getWeight()/Math.pow(height/100,2);
    }

    @Override
    public double getAverageByMonth(String login, String month) {
        List<BodyDimensions> bodyDimensionsList = getBodyDimensionsListForUser(login);
        double sum = 0;
        int counter = 0;
        for (BodyDimensions bodyDimensions : bodyDimensionsList) {
            if (bodyDimensions.getDate().getMonth().toString().equals(month)) {
                counter++;
                sum += bodyDimensions.getHips();
            }
        }
        return sum / counter;
    }

    @Override
    public Iterable<BodyDimensions> getAllBodyDimensions() {
        return bodyDimensionsRepository.findAll();
    }

    public List<BodyDimensions> getBodyDimensionsListForUser(String login) {
        var user = userRepository.findByLogin(login).orElseThrow();
        List<BodyDimensions> bodyDimensionsList = new ArrayList<>();
        for (BodyDimensions i : user.getBodyDimensionsons()) {
            System.out.println("ID" + i.getBodyDimensionsId());
            bodyDimensionsList.add(i);
        }
        Collections.sort(bodyDimensionsList);

        return bodyDimensionsList;
    }


}
