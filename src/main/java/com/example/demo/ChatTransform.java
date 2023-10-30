package com.example.demo;

public class ChatTransform {
    private String salaryRange;
    private String educationRequirements;

    // Constructors, getters, and setters

    public ChatTransform() {
        // Default constructor required for serialization
    }

    public ChatTransform(String salaryRange, String educationRequirements) {
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
