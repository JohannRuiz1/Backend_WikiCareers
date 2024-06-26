/*  DatabaseProperties.java WikiCareers (Johann Ruiz) Virginia Tech
Overhead for the database properties
December 2023
*/ 
package com.example.demo.properities;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DatabaseProperties {

    private String url;
    private String driverClassName;
    private String username;
    private String password;

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }
    
    public String getDriverClassName(){
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName){
        this.driverClassName = driverClassName;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
