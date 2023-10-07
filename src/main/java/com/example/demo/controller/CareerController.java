package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.Response;
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

import com.example.demo.properities.DatabaseProperties;
import com.example.demo.repository.*;
import com.example.demo.models.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.ResultSet;

@RestController
@RequestMapping("/api/careers")
public class CareerController {

    @Autowired
    private CareerRepository careerRepo;

	@Autowired
	private DatabaseProperties databaseProperties;

	@GetMapping("/documentation")
	public String index() {
        // Create swagger page?
		return "Welcome to the Career API Page! Here's documentation";
	}

    @GetMapping("")
    public ResponseEntity<List<Career>> getAllCareers(@RequestParam(required = false) String title){
        try {
            List<Career> careers =  new ArrayList<Career>();
            if (title == null){
                careerRepo.findAll().forEach(careers::add);
            }
            else{
                careerRepo.findByTitleContaining(title).forEach(careers::add); 
            }

            if (careers.isEmpty()){
                return new ResponseEntity<List<Career>>(HttpStatus.NO_CONTENT);
            }
            else{
                return new ResponseEntity<>(careers, HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<String> createCareer(@RequestBody Career career) {
        try {
            careerRepo.save(career);//new Career(career.getTitle(), career.getDescription(), career.getPay_range_low(), career.getPay_range_high(), career.getRisk_level()));
            return new ResponseEntity<>("Career was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	/*public void connect() {
		Connection connection = null;
		try{
			connection = DriverManager.getConnection(databaseProperties.getUrl(), databaseProperties.getUsername(), databaseProperties.getPassword());
			       
            // Get database metadata
            DatabaseMetaData metaData = connection.getMetaData();

			// TODO: Put in the application properties
            String catalog = null;
            String schemaPattern = "CAREERINFO"; 

            // Get a ResultSet of table names using the metadata
            ResultSet tables = metaData.getTables(catalog, schemaPattern, "%", new String[] { "TABLE" });

            // Iterate through the ResultSet to print the table names
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("Table Name: " + tableName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

}