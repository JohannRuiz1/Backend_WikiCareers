package com.example.demo.models;

public class Risk {
    private int riskId;
    private int careerId;
    private String riskDescription;
    private int riskLevel;

    public Risk(){

    }

    public Risk(int careerId, String riskDescription, int riskLevel){
        this.careerId = careerId;
        this.riskDescription = riskDescription;
        this.riskLevel = riskLevel;
    }

    public Risk(int riskId, int careerId, String riskDescription, int riskLevel){
        this(careerId, riskDescription, riskLevel);
        this.riskId = riskId;
    }

    @Override
	public String toString(){
		return "Risk [risk_id=" + riskId + ", career_id=" + careerId + ", risk_description=" + riskDescription + ", risk_level=" + riskLevel + "]";
	}

    public int getRiskId() {
        return this.riskId;
    }

    public void setRiskId(int riskId) {
        this.riskId = riskId;
    }

    public int getCareerId() {
        return this.careerId;
    }

    public void setCareerId(int careerId) {
        this.careerId = careerId;
    }

    public String getRiskDescription() {
        return this.riskDescription;
    }

    public void setRiskDescription(String riskDescription) {
        this.riskDescription = riskDescription;
    }

    public int getRiskLevel() {
        return this.riskLevel;
    }

    public void setRiskLevel(int riskLevel) {
        this.riskLevel = riskLevel;
    }
}
