/*  RiskRepository.java WikiCareers (Johann Ruiz) Virginia Tech
<description>
December 2023
*/ 
package com.example.demo.repository;

import java.util.List;

import com.example.demo.models.Risk;


public interface RiskRepository {

    int save(Risk risk);

    int update(Risk risk);

    Risk findByRiskId(int id);

    int deleteById(int id);
    
    List<Risk> findAll();
}
