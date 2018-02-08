package com.assignment.models;

import java.util.Date;

public class PlanRequest {

	private String loanAmount;
	private String nominalRate;
	private int duration;
	private Date startDate;

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getNominalRate() {
		return nominalRate;
	}

	public void setNominalRate(String nominalRate) {
		this.nominalRate = nominalRate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
