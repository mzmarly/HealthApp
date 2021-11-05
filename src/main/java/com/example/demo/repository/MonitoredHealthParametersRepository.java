package com.example.demo.repository;

import com.example.demo.model.MonitoredHealthParametersInfo.MonitoredHealthParameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoredHealthParametersRepository extends JpaRepository<MonitoredHealthParameters,Long> {
}
