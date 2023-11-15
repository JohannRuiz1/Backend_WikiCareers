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

import com.example.demo.models.Risk;
import com.example.demo.repository.RiskRepository;

@CrossOrigin(origins = "http://localhost:3200")
@RestController
@RequestMapping("/api/risks")
public class RiskController {

    @Autowired
    private RiskRepository riskRepo;

    @PostMapping("")
    public ResponseEntity<String> createRisk(@RequestBody Risk risk) {
        try {
            riskRepo.save(risk);
            return new ResponseEntity<>("Risk was created successfully.", HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Risk>> getAllRisks() {
        try {
            List<Risk> risks = new ArrayList<Risk>();

            riskRepo.findAll().forEach(risks::add);

            if (risks.isEmpty()) {
                return new ResponseEntity<List<Risk>>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(risks, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Risk> getRiskById(@PathVariable("id") int id) {
        try {
            Risk education = riskRepo.findByRiskId(id);

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
    public ResponseEntity<String> updateRisk(@PathVariable("id") int id, @RequestBody Risk education) {

        try {
            Risk _risk = riskRepo.findByRiskId(id);

            if (_risk != null) {
                _risk.setRiskDescription(education.getRiskDescription());
                _risk.setRiskLevel(education.getRiskLevel());

                riskRepo.update(_risk);
                return new ResponseEntity<>("Risk was updated successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot find risk with id=" + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteRisk(@PathVariable("id") int id) {
        try {
            int result = riskRepo.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Risk with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Risk was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Cannot delete education.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
