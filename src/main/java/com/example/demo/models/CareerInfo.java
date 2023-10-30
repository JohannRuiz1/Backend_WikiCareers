package com.example.demo.models;

public class CareerInfo {
    private String title;
    private String description;
    private double payRangeLow;
    private double payRangeHigh;
    private int riskLevel;
    private String educationLevel;
    private int yearsOfSchooling;
    private String educationDescription;
    private String riskDescription;


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPayRangeLow() {
        return this.payRangeLow;
    }

    public void setPayRangeLow(double payRangeLow) {
        this.payRangeLow = payRangeLow;
    }

    public double getPayRangeHigh() {
        return this.payRangeHigh;
    }

    public void setPayRangeHigh(double payRangeHigh) {
        this.payRangeHigh = payRangeHigh;
    }

    public int getRiskLevel() {
        return this.riskLevel;
    }

    public void setRiskLevel(int riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getEducationLevel() {
        return this.educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public int getYearsOfSchooling() {
        return this.yearsOfSchooling;
    }

    public void setYearsOfSchooling(int yearsOfSchooling) {
        this.yearsOfSchooling = yearsOfSchooling;
    }

    public String getEducationDescription() {
        return this.educationDescription;
    }

    public void setEducationDescription(String educationDescription) {
        this.educationDescription = educationDescription;
    }

    public String getRiskDescription() {
        return this.riskDescription;
    }

    public void setRiskDescription(String riskDescription) {
        this.riskDescription = riskDescription;
    }
}