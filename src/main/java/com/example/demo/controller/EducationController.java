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

import com.example.demo.models.Education;
import com.example.demo.repository.EducationRepository;

@RestController
@RequestMapping("/api/education")
public class EducationController {

    @Autowired
    private EducationRepository educationRepo;

    @PostMapping("")
    public ResponseEntity<String> createEducation(@RequestBody Education education){
        try {
            System.out.println(education.toString());
            educationRepo.save(education);
            return new ResponseEntity<>("Education was created successfully.", HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Education>> getAllEducation(){
        try {
            List<Education> educations =  new ArrayList<Education>();
            
            educationRepo.findAll().forEach(educations::add);
        
            if (educations.isEmpty()){
                return new ResponseEntity<List<Education>>(HttpStatus.NO_CONTENT);
            }
            else{
                return new ResponseEntity<>(educations, HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
}
