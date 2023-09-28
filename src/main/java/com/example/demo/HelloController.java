package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


@RestController
public class HelloController {

	@Autowired
	private DatabaseProperties databaseProperties;

	@GetMapping("/")
	public String index() {
		connect();
		return "Greetings from Spring Boot!";
	}

	public void connect() {
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
    }

}