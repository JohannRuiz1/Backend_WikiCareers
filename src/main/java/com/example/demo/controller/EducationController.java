package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Education;
import com.example.demo.repository.EducationRepository;

@CrossOrigin(origins = "http://localhost:3200")
@RestController
@RequestMapping("/api/education")
public class EducationController {

    @Autowired
    private EducationRepository educationRepo;

    @PostMapping("")
    public ResponseEntity<String> createEducation(@RequestBody Education education) {
        try {
            educationRepo.save(education);
            return new ResponseEntity<>("Education was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Education>> getAllEducation() {
        try {
            List<Education> educations = new ArrayList<Education>();

            educationRepo.findAll().forEach(educations::add);

            if (educations.isEmpty()) {
                return new ResponseEntity<List<Education>>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(educations, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Education> getEducationById(@PathVariable("id") int id) {
        try {
            Education education = educationRepo.findByEducationId(id);

            if (education != null) {
                return new ResponseEntity<>(education, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/id/{id}")
    public ResponseEntity<String> updateEducation(@PathVariable("id") int id, @RequestBody Education education) {

        try {
            Education _education = educationRepo.findByEducationId(id);

            if (_education != null) {
                _education.setEducationDescription(education.getEducationDescription());
                _education.setEducationLevel(education.getEducationLevel());
                _education.setYearsOfSchooling(education.getYearsOfSchooling());

                educationRepo.update(_education);
                return new ResponseEntity<>("Education was updated successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot find education with id=" + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteEducation(@PathVariable("id") int id) {
        try {
            int result = educationRepo.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Education with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Education was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Cannot delete education.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
