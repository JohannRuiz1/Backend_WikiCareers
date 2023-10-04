package com.example.demo;

public class JsonResponse {
    private String salaryRange;
    private String educationRequirements;

    // Constructors, getters, and setters

    public JsonResponse() {
        // Default constructor required for serialization
    }

    public JsonResponse(String salaryRange, String educationRequirements) {
        this.salaryRange = salaryRange;
        this.educationRequirements = educationRequirements;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getEducationRequirements() {
        return educationRequirements;
    }

    public void setEducationRequirements(String educationRequirements) {
        this.educationRequirements = educationRequirements;
    }
}
