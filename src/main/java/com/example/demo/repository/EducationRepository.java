package com.example.demo.repository;

import java.util.List;

import com.example.demo.models.Education;

public interface EducationRepository {
    // You can add custom query methods here if needed
    int save(Education education);

    int update(Education education);

    Education findByEducationId(int id);

    Education findByCareerId(int id);

    int deleteById(int id);

    List<Education> findAll();

}