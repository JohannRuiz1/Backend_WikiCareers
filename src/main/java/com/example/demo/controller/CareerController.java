package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.*;
import com.example.demo.models.*;

@CrossOrigin(origins = "http://localhost:3200")
@RestController
@RequestMapping("/api/careers")
public class CareerController {

  @Autowired
  private CareerRepository careerRepo;

  @GetMapping("")
  public ResponseEntity<List<Career>> getAllCareers(@RequestParam(required = false) String title) {
    try {
      List<Career> careers = new ArrayList<Career>();

      careerRepo.findAll().forEach(careers::add);

      if (careers.isEmpty()) {
        return new ResponseEntity<List<Career>>(HttpStatus.NO_CONTENT);
      } else {
        return new ResponseEntity<>(careers, HttpStatus.OK);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("")
  public ResponseEntity<String> createCareer(@RequestBody Career career) {
    try {
      careerRepo.save(career);
      return new ResponseEntity<>("Created Career Successfully", HttpStatus.CREATED);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<Career> getCareerById(@PathVariable("id") int id) {
    try {
      Career career = careerRepo.findById(id);

      if (career != null) {
        return new ResponseEntity<>(career, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping("/title/{title}")
  public ResponseEntity<Career> getCareerByTitle(@PathVariable("title") String title) {
    try {
      Career career = careerRepo.findByName(title);

      if (career != null) {
        return new ResponseEntity<>(career, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/id/{id}")
  public ResponseEntity<String> updateCareer(@PathVariable("id") int id, @RequestBody Career career) {
    try {
      Career _career = careerRepo.findById(id);

      if (_career != null) {
        _career.setTitle(career.getTitle());
        _career.setDescription(career.getDescription());
        _career.setPay_range_low(career.getPay_range_low());
        _career.setPay_range_high(career.getPay_range_high());
        _career.setRisk_level(career.getRisk_level());

        careerRepo.update(_career);
        return new ResponseEntity<>("Career was updated successfully.", HttpStatus.OK);
      } else {
        return new ResponseEntity<>("Cannot find Career with id=" + id, HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("Cannot delete tutorial.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/id/{id}")
  public ResponseEntity<String> deleteCareer(@PathVariable("id") int id) {
    try {
      int result = careerRepo.deleteById(id);
      if (result == 0) {
        return new ResponseEntity<>("Cannot find Career with id=" + id, HttpStatus.OK);
      }
      return new ResponseEntity<>("Career was deleted successfully.", HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("Cannot delete tutorial.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}