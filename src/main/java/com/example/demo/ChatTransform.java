package com.example.demo;

public class ChatTransform {
    private String salaryRange;
    private String educationRequirements;
    private String description;
    private String riskDescription;

    // Constructors, getters, and setters

    public ChatTransform() {
        // Default constructor required for serialization
    }

    public ChatTransform(String salaryRange, String educationRequirements, String description, String riskDescription) {
        this.salaryRange = salaryRange;
        this.educationRequirements = educationRequirements;
        this.description = description;
        this.riskDescription = riskDescription;
    }
    
    public String getDescription()
    {
        return this.description;
    }

    public String getRiskDescription()
    {
        return this.riskDescription;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setRiskDescription(String riskDescription)
    {
        this.riskDescription = riskDescription;
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
