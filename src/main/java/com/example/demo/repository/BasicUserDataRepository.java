package com.example.demo.repository;

import com.example.demo.model.BasicUserDataInfo.BasicUserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicUserDataRepository extends JpaRepository<BasicUserData,Long> {
}
