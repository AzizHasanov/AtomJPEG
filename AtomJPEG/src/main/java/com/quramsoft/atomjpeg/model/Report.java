package com.quramsoft.atomjpeg.model;

public class Report {

	private String  numberOfImages;
	private String bandwidth;
	private String sizeReduction;
	private String fasterLoadTime;
	private String annualCDNSavings;
	
	public Report()
	{
		
	}
	
	public String getNumberOfImages() {
		return numberOfImages;
	}
	public void setNumberOfImages(String numberOfImages) {
		this.numberOfImages = numberOfImages;
	}
	public String getBandwidth() {
		return bandwidth;
	}
	public void setBandwidth(String bandwidth) {
		this.bandwidth = bandwidth;
	}
	public String getSizeReduction() {
		return sizeReduction;
	}
	public void setSizeReduction(String sizeReduction) {
		this.sizeReduction = sizeReduction;
	}
	public String getFasterLoadTime() {
		return fasterLoadTime;
	}
	public void setFasterLoadTime(String fasterLoadTime) {
		this.fasterLoadTime = fasterLoadTime;
	}
	public String getAnnualCDNSavings() {
		return annualCDNSavings;
	}
	public void setAnnualCDNSavings(String annualCDNSavings) {
		this.annualCDNSavings = annualCDNSavings;
	}


	
}
