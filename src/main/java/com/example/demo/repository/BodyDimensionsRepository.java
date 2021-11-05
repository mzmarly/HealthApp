package com.example.demo.repository;

import com.example.demo.model.BodyDimensionsInfo.BodyDimensions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyDimensionsRepository extends JpaRepository<BodyDimensions,Long> {
}
