package com.example.demo.repository;

import java.util.List;

import com.example.demo.models.Career;

public interface CareerRepository {
    // You can add custom query methods here if needed
    int save(Career career);

    int update(Career career);

    Career findById(int id);

    int deleteById(int id);

    List<Career> findAll();

    public List<Career> findByTitleContaining(String title);
}