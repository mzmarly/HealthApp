package com.example.demo.service;

import com.example.demo.model.BasicUserDataInfo.BasicUserData;
import com.example.demo.model.BasicUserDataInfo.PhysicalActivity;
import com.example.demo.model.BasicUserDataInfo.Sex;

public interface BasicUserDataService {

    BasicUserData create(BasicUserData basicUserData);

    BasicUserData addBasicUserData(String login, int age, Sex sex, PhysicalActivity physicalActivity);

    void removeBasicUserData(Long id);
}
