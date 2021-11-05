package com.example.demo.serviceImpl;

import com.example.demo.model.BasicUserDataInfo.BasicUserData;
import com.example.demo.model.BasicUserDataInfo.PhysicalActivity;
import com.example.demo.model.BasicUserDataInfo.Sex;
import com.example.demo.repository.BasicUserDataRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BasicUserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BasicUserDataServiceImpl implements BasicUserDataService {

    @Autowired
    BasicUserDataRepository basicUserDataRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public BasicUserData create(BasicUserData basicUserData) {
        return basicUserDataRepository.save(basicUserData);
    }

    @Override
    public BasicUserData addBasicUserData(String login, int age, Sex sex, PhysicalActivity physicalActivity) {
        long basicUserDataId = basicUserDataRepository.findAll().size()+1;
        var user = userRepository.findByLogin(login).orElseThrow();
        BasicUserData basicUserData = new BasicUserData(basicUserDataId, age, sex, physicalActivity);
        basicUserDataRepository.save(basicUserData);
        Set<BasicUserData> basicUserDataSet=user.getBasicUserData();
        basicUserDataSet.add(basicUserData);
        user.setBasicUserData(basicUserDataSet);
        user.getBasicUserData().add(basicUserData);
        userRepository.save(user);
        return basicUserData;
    }

    @Override
    public void removeBasicUserData(Long id) {
        basicUserDataRepository.deleteById(id);
    }


}
