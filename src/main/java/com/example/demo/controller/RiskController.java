package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Risk;
import com.example.demo.repository.RiskRepository;

@RestController
@RequestMapping("/api/risk")
public class RiskController {
    
    @Autowired
    private RiskRepository riskRepo;

    @PostMapping("")
    public ResponseEntity<String> createRisk(@RequestBody Risk risk){
        try {
            riskRepo.save(risk);
            return new ResponseEntity<>("Risk was created successfully.", HttpStatus.CREATED);

        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<List <Risk>> getAllRisks(){

    }
}
