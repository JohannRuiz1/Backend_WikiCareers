package com.example.demo.models;

public class Education {

	private int educationId;
	private int careerId;
	private String educationLevel;
	private int yearsOfSchooling;
	private String educationDescription;


	public Education(){
	}

	public Education(int careerId, String educationLevel, int yearsOfSchooling, String educationDescription){
		this.careerId = careerId;
		this.educationLevel = educationLevel;
		this.yearsOfSchooling = yearsOfSchooling;
		this.educationDescription = educationDescription;
	}      
	

	public Education(int educationId, int careerId, String educationLevel, int yearsOfSchooling, String educationDescription) {
		this(careerId, educationLevel, yearsOfSchooling, educationDescription);
		this.educationId = educationId;
	}

	@Override
	public String toString(){
		return "Education [educationId=" + educationId + ", careerId=" + careerId + ", educationLevel=" + educationLevel + ", yearsOfSchooling=" + yearsOfSchooling +", educationDescription=" + educationDescription + "]";
	}

	public int getEducationId() {
		return this.educationId;
	}

	public void setEducationId(int educationId) {
		this.educationId = educationId;
	}

	public int getCareerId() {
		return this.careerId;
	}

	public void setCareerId(int careerId) {
		this.careerId = careerId;
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




}
