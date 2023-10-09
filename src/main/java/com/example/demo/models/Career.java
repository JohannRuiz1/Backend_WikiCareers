package com.example.demo.models;

public class Career {
    private int career_id;
    private String title; 
    private String description;
    private double pay_range_low;
    private double pay_range_high;
	private int risk_level;

	public Career(){
	}

	public Career(String title, String description, double pay_range_low, double pay_range_high, int risk_level) {
		this.title = title;
		this.description = description;
		this.pay_range_low = pay_range_low;
		this.pay_range_high = pay_range_high;
		this.risk_level = risk_level;
	}


	public Career(int career_id, String title, String description, double pay_range_low, double pay_range_high, int risk_level) {
		this(title, description, pay_range_low, pay_range_high, risk_level);
		this.career_id =  career_id;
	}

	@Override
	public String toString(){
		return "Career [career_id=" + career_id + ", title=" + title + ", pay_range_low=" + pay_range_low + ", pay_range_high=" + pay_range_high + ", risk_level=" + risk_level + "]";
	}

	public int getCareer_id() {
		return this.career_id;
	}

	public void setCareer_id(int career_id) {
		this.career_id = career_id;
	}

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

	public double getPay_range_low() {
		return this.pay_range_low;
	}

	public void setPay_range_low(double pay_range_low) {
		this.pay_range_low = pay_range_low;
	}

	public double getPay_range_high() {
		return this.pay_range_high;
	}

	public void setPay_range_high(double pay_range_high) {
		this.pay_range_high = pay_range_high;
	}

	public int getRisk_level() {
		return this.risk_level;
	}

	public void setRisk_level(int risk_level) {
		this.risk_level = risk_level;
	}


}
